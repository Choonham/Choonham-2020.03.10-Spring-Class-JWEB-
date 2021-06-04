function searchFamily() {
	var url = "../familyMgr/searchFamilyGroup.jsp?search=f";
	window.open(url, "post", "width=1300, height=900");
}

function loadSearch() {
	frm = document.searchForm; 
	frm.action="searchFamilyGroup.jsp"; 
	frm.submit()
}