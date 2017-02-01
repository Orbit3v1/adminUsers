function synchronizeCheckboxes(headerCheckbox, targetSuffix) {
    var elements = getElementsBySuffixId(targetSuffix);
    for (var i = 0; i < elements.length; i++) {
        elements[i].checked = headerCheckbox.checked;
    }
}

function synchronizeCheckboxHeader(targetId, targetSuffix) {
    var checkbox = document.getElementById("mainForm:persons:".concat(targetId));
    checkbox.checked = true;

    var elements = getElementsBySuffixId(targetSuffix);
    for (var i = 0; i < elements.length; i++) {
        if (!elements[i].checked) {
            checkbox.checked = false;
            break;
        }
    }
}

function getElementsBySuffixId(suffix) {
    var elements = [];
    var inputs = document.getElementsByTagName("input");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].id.indexOf(suffix) > -1) {
            elements.push(inputs[i]);
        }
    }
    return elements;
}

function initCheckboxHeader(){
    synchronizeCheckboxHeader('cbExecuteHeader', 'cbExecute');
    synchronizeCheckboxHeader('cbEditHeader', 'cbEdit');
    synchronizeCheckboxHeader('cbWriteHeader', 'cbWrite');
    synchronizeCheckboxHeader('cbReadHeader', 'cbRead');
}