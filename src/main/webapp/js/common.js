function openPopUp(source, name){
    var popUp = window.open(source.replace(/\|\|/g, '&'), name, 'width=840,height=750,resizable=yes,scrollbars=yes');
    return popUp;
}

function openPopUpFull(source, name){
    var w = screen.width * 0.9;
    var popUp = window.open(source.replace(/\|\|/g, '&'), name, 'width=' + w + ',height=750,resizable=yes,scrollbars=yes');
    return popUp;
}

function clickIn(elementId){
    var element = this.document.getElementById(elementId);
    element.click();
}

function save() {
    refreshOpener();
}

function exit() {
    var opener = window.opener;
    if (opener) {
        opener.focus();
    }
    window.close();

}

function saveExit() {
    save();
    exit();
}

function refreshOpener() {
    var opener = window.opener;
    if (!opener)
        return;
    var refreshButton = opener.document.getElementById("mainForm:refresh");
    if (refreshButton == null) {
        refreshButton = opener.document.getElementById("closeForm:refresh");
    }
    if (refreshButton != null) {
        refreshButton.click();
    }
}