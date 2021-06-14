package board.dao;

/**	Connection, PreparedStatement, 쿼리 실행관련**/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//-------------------------------------------------------
/**	Context(Interface이다.), InitialContext 객체 **/
//	lookup(찾고자하는 이름(JNDI명)) -> 탐색기에서 검색하는 것과 같은 느낌
import javax.naming.Context;
import javax.naming.InitialContext;
//---------------------------------------------------
/**	추가 (JNDI 방식) **/
//	DataSource 객체 -> getConnection()
import javax.sql.DataSource;

import board.command.BoardCommand;
import board.dto.BoardDTO;

public class BoardDAO {

	DataSource ds; 

	// 생성자 : DataSource 얻기 : InitialContext와 JNDI명
	public BoardDAO() {
		try {
			// InitialContext ctx=new InitialContext(); 이것도 가능.
			Context ctx = new InitialContext();

			// lookup("java:comp/env/찾고자하는 JNDI이름")
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("ds : " + ds);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 글 목록 조회 메서드 */
	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

		try {
			String sql = "SELECT * FROM springboard ORDER BY num desc";
			Connection con = ds.getConnection();
			// Connection con = pool.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BoardDTO data = new BoardDTO();

				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setDate(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));

				list.add(data);
			} // end while

			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/* 글 번호 얻기 메서드 */
	// insert into 할때 마다 자동으로 글번호를 증가시키기 위한 메서드
	public int getNewNum() {
		int newNum = 1;
		try {
			String sql = "select max(num) from springboard";
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				// 조회결과 가상필드(max(num))이기 때문에, 
				// 필드명을 사용할 수 없어서 필드인덱스(1)를 사용.
				newNum = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newNum;
	}

	/* 글 쓰기 메서드 (개별) */
	// public void write(BoardDTO boardDTO){} 가 정석!!!.
	// 그러나, 3개만 받기위해 아래와 같이 작성.
	public void write(String author, String title, String content) {
		try {
			int newNum = getNewNum(); // 최대값을 구해오는 메소드 호출
			System.out.println("newNum : " + newNum);

			String sql = "insert into springboard(num,author,title,content) values(";
			sql += newNum + ",'" + author + "','" + title + "','" + content + "')";

			System.out.println(sql); // 한글 깨지는지 확인

			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute(sql); // == stmt.executeUpdate(sql);

			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 글 쓰기 메서드 (객체) */
	public void write(BoardCommand data) {
		try {
			int newNum = getNewNum(); // 최대값을 구해오는 메소드 호출
			System.out.println("newNum : " + newNum);

			String sql = "insert into springboard (num, author, title, content) values (?,?,?,?)";
			System.out.println(sql); // 한글 깨지는지 확인

			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, newNum);
			stmt.setString(2, data.getAuthor());
			stmt.setString(3, data.getTitle());
			stmt.setString(4, data.getContent());
			
			stmt.executeUpdate();

			stmt.close();	
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  // write() END

	
	
	
	
	
	/* 글 자세히 보기 메서드 */
	public BoardDTO retrieve(String num) {
		BoardDTO data = new BoardDTO();

		try {
			// 1. 조회수 증가시키기
			String sql = "update springboard set readcnt=readcnt+1 where num=?";
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(num));

			int update = stmt.executeUpdate();
			System.out.println("BoardDAO 에서 retrieve 조회수 증가유무(update) : " + update);

			// 2. 글 번호를 이용하여 선택된 글만 조회
			stmt = null; // 한 개의 stmt을 2번 쓸 수 있는 방법.
			sql = "select * from springboard where num=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(num));
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
			} // end while

			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	} // 

	/* 글 수정하기 메서드 
	public void update(String num, String author, String title, String content) {
		try {
			String sql = "update springboard set title='" + title + "',";
			sql += " content='" + content + "',";
			sql += " author ='" + author + "'";
			sql += " where num=" + num;
			System.out.println(sql);

			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate(sql);

			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	/* 글 수정하기 메서드 */
	public void update(BoardCommand data, String num) {
		try {
			String sql = "update springboard set title=?, content=?, author =? where num=?";
			
			Connection con = ds.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, data.getTitle());
			stmt.setString(2, data.getContent());
			stmt.setString(3, data.getAuthor());
			stmt.setInt(4, Integer.parseInt(num));
			
			stmt.executeUpdate();

			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 글 삭제하기 메서드 */
	public void delete(String num) {
		try {
			String sql = "delete springboard where num=?";
			System.out.println(sql);

			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(num));
			stmt.executeUpdate();

			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	/* 검색하기 메서드 */
	public ArrayList<BoardDTO> search(String name, String value) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			String sql = "SELECT * FROM springboard";
			sql += " WHERE  " + name + " LIKE  '%" + value + "%' ";
			System.out.println(sql);

			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BoardDTO data = new BoardDTO();
				
				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setDate(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				
				list.add(data);
			}
			
			rs.close();	stmt.close();	con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


}
