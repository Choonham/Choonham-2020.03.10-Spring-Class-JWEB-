package com.choonham.mpd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

public class ConnectionDAO {

	private ConnectionDAO() {
	}
	
	ArrayList<ConnectionObject> connList = new ArrayList<>();
	private String _driver = "oracle.jdbc.driver.OracleDriver";
	private String _url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String _user = "petmgr";
	private String _pwd = "6725";

	private boolean _traceOn = false; // 사용 가능 여부
	private boolean initialize = false; // 초기화 여부
	private int _openConnections = 50; // 최대 connection

	private static ConnectionDAO instance = null; // 싱글톤 패턴을 사용하기 위한 인스턴스

	public static ConnectionDAO getInstance() {
		if (instance == null) {
			instance = new ConnectionDAO();
		}
		return instance;
	}

	/** 열려있는 Connection 객체의 수 설정 **/
	public void setOpenConnectionCount(int count) {
		_openConnections = count;
	}
	
	/** 사용 가능 여부 설정 **/
	public void setEnableTrace(boolean enable) {
		_traceOn = enable;
	}

	/** connections Vector 를 반환 **/
	public ArrayList<ConnectionObject> getConnectionList() {
		return connList;
	}

	/** 현재 열려있는 Connection 객체의 수를 반환 **/
	public int getConnectionCount() {
		return connList.size();
	}

	/** 새로운 Connection 객체 생성 **/
	private Connection createConnection() {
		Connection conn = null;

		try {
			if (_user == null) {
				_user = "";
			}
			if (_pwd == null) {
				_pwd = "";
			}

			Properties props = new Properties();
			props.put("user", _user);
			props.put("password", _pwd);

			conn = DriverManager.getConnection(_url, props);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/** 모든 연결을 닫고, 연결 pool(vector) 내 모든 내용을 삭제 **/
	public void finalize() {
		ConnectionObject conns = null;

		for (int i = 0; i < connList.size(); i++) {
			conns = (ConnectionObject) connList.get(i);
			try {
				conns.connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conns = null;
		}

		connList.clear();
	}

	/** 현재 사용되지 않지만, 연결되어 있는 Connection 자원 해제 **/
	public void releaseFreeConnections() {
		ConnectionObject conns = null;
		for (int i = 0; i < connList.size(); i++) {
			conns = (ConnectionObject) connList.get(i);
			if (!conns.inuse) {
				removeConnection(conns.connection);
			}
		}
	}

	/** Connection 객체 제거 **/
	public void removeConnection(Connection c) {
		if (c == null)
			return;
		ConnectionObject conns = null;
		for (int i = 0; i < connList.size(); i++) {
			conns = (ConnectionObject) connList.get(i);
			if (c == conns.connection) {
				try {
					c.close();
					connList.remove(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	/** PreparedStatement, Statement, ResultSet 등 다양한 자원 해제 메서드(오버라이딩) **/
	public void freeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection conn, Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void freeConnection(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 새로운 Connection 객체를 반환 **/
	public Connection getConnection() {
		Connection conn = null;
		ConnectionObject conns = null;

		if (!initialize) {
			try {
				Class.forName(_driver);
				conn = DriverManager.getConnection(_url, _user, _pwd);
			} catch (Exception e) {
				System.err.print(e);
			}
		}

		boolean badConnection = false;

		for (int i = 0; i < connList.size(); i++) {
			conns = (ConnectionObject) connList.get(i);

			// 연결이 유효한 지 테스트
			if (!conns.inuse) {
				try {
					badConnection = conns.connection.isClosed();

					if (!badConnection) {
						badConnection = (conns.connection.getWarnings() != null); // getWarnings(): 경고 발생(true), else(false)
					}
				} catch (Exception e) {
					System.err.print(e);
				}
				if (badConnection) {
					connList.remove(i);
					continue;
				}

				conn = conns.connection;
				conns.inuse = true;
				break;
			}
			// 연결이 유효한 지 테스트
			
			if (conn == null) {
				conn = createConnection();
				conns = new ConnectionObject(conn, true);
				connList.add(conns);
			}
		}

		return conn;

	}

}

/** Connection 객체와 그 객체의 사용 여부를 저장할 수 있는 서브 클래스 선언 * */
class ConnectionObject {
	public Connection connection = null;
	public boolean inuse = false;

	public ConnectionObject(Connection c, boolean useFlag) {
		connection = c;
		inuse = useFlag;
	}

}
