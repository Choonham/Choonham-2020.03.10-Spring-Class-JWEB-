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
	location.href = "../modifyComm_proc.do?flag=like&no="+no;
}

function friendSearch(){
	url="addFriend.jsp?search=f";
	window.open(url, "post", "width=500, height=300");
}

function msgView(code, to, content, time, from){
	url="../msg_detail.do?code="+ code;
	window.open(url, "post", "width=800, height=600");
}

function msgSend(to, from){
	url="../message/messageSend.jsp?to="+to+"&from="+from; 
	window.open(url, "post", "width=800, height=600");
}

function openDiary(diaryHost){
	url="../friend_diary.do?diaryHost=" + diaryHost;
	window.open(url, "post", "width=1500, height=1000");
}

function check() {
	if(document.infoForm.pwd.value==""){
		alert("비밀번호를 입력해주세요");
		document.infoForm.pwd.focus();
		return;
	}
	if(document.infoForm.originPwd.value != document.infoForm.pwd.value){
		alert("비밀번호를가 일치하지 않습니다.");
		document.infoForm.pwd.focus();
		return;
	}
	document.infoForm.submit();
}

function searchFamily() {
	var url = "../familyMgr/searchFamilyGroup.jsp?search=n";
	window.open(url, "post", "width=800, height=800");
}



