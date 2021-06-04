function idCheck(id) {
	if (document.regForm.id.value == "") {
		alert('아이디를 입력하여 주십시오.');
		document.regForm.id.focus();
		return;
	}
	var url = "../idcheck.do?id=" + id;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function idok(userid) {
	opener.regForm.id.value = document.frm.id.value;
	opener.regForm.reid.value = document.frm.id.value;
	self.close();
}


function win_close(){
	self.close();
}

function inputCheck() {
	if(document.regForm.repwd.value==""){
		alert("비밀번호를 입력해주세요");
		document.regForm.repwd.focus();
		return;
	}
	if(document.regForm.repwd.value != document.regForm.pwd.value){
		alert("비밀번호를가 일치하지 않습니다.");
		document.regForm.repwd.focus();
		return;
	}
	document.regForm.submit();
}

function pushLike(no) {
	alert("추천하였습니다");
	location.href = "/my_pet_diaries/modifyComm_proc.do?flag=like&no="+no;
}







