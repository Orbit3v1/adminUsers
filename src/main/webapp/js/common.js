function openPopUp(source, name){
    var popUp = window.open(source, name, 'width=640,height=550,resizable=yes,scrollbars=yes');
    return popUp;
}

function clickIn(elementId){
    var element = this.document.getElementById(elementId);
    element.click();
}