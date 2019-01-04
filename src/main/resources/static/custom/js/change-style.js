
function changeActiveMenu(activeMenu) {
    var menuList = new Array("patient", "overview");
    for (var i = 0; i < menuList.length; i++) {
        if (menuList[i] != activeMenu) {
            var toRemoveView = document.getElementById(menuList[i]);
            toRemoveView.removeAttribute("class");
        }
    }
    var toAddView = document.getElementById(activeMenu);
    toAddView.setAttribute("class", "active-menu");
}

