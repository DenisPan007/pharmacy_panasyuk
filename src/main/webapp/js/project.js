function getXMLHttpRequest() {
    var xmlHttpReq;
    // to create XMLHttpRequest object in non-Microsoft browsers
    if (window.XMLHttpRequest) {
        xmlHttpReq = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        try {
            //to create XMLHttpRequest object in later versions of Internet Explorer
            xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (exp1) {
            try {
                //to create XMLHttpRequest object in later versions of Internet Explorer
                xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (exp2) {
                //xmlHttpReq = false;
                alert("Exception in getXMLHttpRequest()!");
            }
        }
    }
    return xmlHttpReq;
}

function addItemToCart(button, itemList, index) {
    var item = JSON.parse(itemList)[index];
    var id = item.drug.id;
    var inputTag = document.getElementById(id);
    var amount = inputTag.value;
    if (amount <= 0 || amount > item.drug.availableAmount) {
        $(inputTag).addClass("border-danger");
        $(inputTag).focus((function () {
            $(inputTag).removeClass("border-danger");
        }));
        return;
    }
    item.amount = amount;
    var cookieCartString = $.cookie('cart');
    var trigger = true;
    if (cookieCartString != null) {
        var cookieCartJson = JSON.parse(decodeURIComponent(cookieCartString));
        // alert('old cookies   :' + cookieCartString);
        for (var i = 0; i < cookieCartJson.length; ++i) {
            var oldItem = cookieCartJson[i];
            if (oldItem.drug.id === item.drug.id) {
                trigger = false;
                totalUpdate(item.drug.price * item.amount - oldItem.drug.price * oldItem.amount);
                cookieCartJson[i] = item;
            }
        }
        if (trigger) {
            cookieCartJson.push(item);
            totalUpdate(item.drug.price * item.amount);
        }
        newCookieJson = cookieCartJson;
    } else {
        newCookieJson = [];
        newCookieJson.push(item);
        totalUpdate(item.drug.price * item.amount);
    }
    $('#cartBlock').show();
    $('#emptyCartMessage').hide();
    var newCookieString = JSON.stringify(newCookieJson);
    $.cookie('cart', newCookieString);
    var tbodyCartTag = $('#tbodyTagCart');
    if (tbodyCartTag !== null) {
        var body = 'command=' + encodeURIComponent("updateCart");
        var req = getXMLHttpRequest();
        req.onreadystatechange = function () {
            if (req.readyState === 4) {
                if (req.status === 200) {
                    var responseText = req.responseText;
                    tbodyCartTag.empty().append(responseText);
                    itemAmountUpdate();
                    $("#myModal").modal('hide');
                } else {
                    alert("can't update cart");
                }
            }
        };
        req.open('POST', '/pharmacy/', true);
        req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        req.send(body);
    }
}

function getDrugsByName(drugName) {
    var body = 'command=' + encodeURIComponent("getItemsByName") + '&name=' + encodeURIComponent(drugName);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                var responseText = req.responseText;
                $('#tbodyTagModal').empty().append(responseText);
                $("#myModal").modal();
            } else {
                alert("can't get drugs by name");
            }
        }
    };
    req.open('POST', '/pharmacy/', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function getDrugsFromBase() {
    var body = 'command=' + encodeURIComponent("getDrugList");
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                var jsonText = req.responseText;
                var json = JSON.parse(jsonText);
                mySearch(json);
            } else {
                alert("can't get drugs");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function mySearch(drugList) {
    $("#search").autocomplete({
        source: drugList,
        select: function (event, ui) {
            getDrugsByName(ui.item.value);
            return false;
        }

    });
}

function deleteDrugFromCart(id, button) {
    var cookieCartString = $.cookie('cart');
    var cookieCartJson = JSON.parse(decodeURIComponent(cookieCartString));
    for (var i = 0; i < cookieCartJson.length; ++i) {
        if (cookieCartJson[i].drug.id === id) {
            totalUpdate((-1) * cookieCartJson[i].drug.price * cookieCartJson[i].amount);
            cookieCartJson.splice(i, 1);
        }
    }
    var newCookieString = JSON.stringify(cookieCartJson);
    $.cookie('cart', newCookieString);
    itemAmountUpdate();
    if (cookieCartJson.length == 0) {
        $('#cartBlock').hide();
        $('#emptyCartMessage').show();

    }
    var tdTag = button.parentElement;
    var trTag = tdTag.parentElement;
    trTag.parentElement.removeChild(trTag);
}

function checkout() {
    var body = 'command=' + encodeURIComponent("isUserLogin");
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                if (req.responseText === "true") {
                    document.location.href = 'pharmacy/?command=makeOrder';
                } else {
                    document.location.href = 'pharmacy/?command=toLogin';
                }
            } else {
                alert("can't get response ");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function getPrescription(id, button) {
    var body = 'command=' + encodeURIComponent("getPrescription") + '&drugId=' + encodeURIComponent(id);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                if (req.responseText === "notLogined") {
                    document.location.href = 'pharmacy/?command=toLogin';
                } else if (req.responseText === 'alreadyHave') {
                    alert('you already quire this prescription');
                    $(button).removeAttr("onclick");
                } else if (req.responseText === 'ok') {
                    alert('Ok! You just quire the prescription');
                    $(button).removeAttr("onclick");
                } else if (req.responseText === 'valid') {
                    alert('Your prescription is still valid');
                    $(button).removeAttr("onclick");
                } else {
                    alert('something going wrong...')
                }
            } else {
                alert("can't get prescription ");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function confirmReceipt(id, button) {
    var body = 'command=' + encodeURIComponent("confirmReceipt") + '&id=' + encodeURIComponent(id);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                $('#orderBody').empty().append(req.responseText);
            } else {
                alert("can't confirm");
            }
        }
    };
    req.open('POST', '/pharmacy/', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function addDrugToBase() {
    var name = document.getElementById('inputName').value;
    var releaseForm = document.getElementById('releaseForm').value;
    var manufacturer = document.getElementById('manufacturer').value;
    var prescription = document.getElementById('inputPrescription').value;
    var price = document.getElementById('inputPrice').value;
    var availableAmount = document.getElementById('inputAvailableAmount').value;
    var body = 'command=' + encodeURIComponent("addDrug")
        + '&name=' + encodeURIComponent(name) + '&releaseForm=' + encodeURIComponent(releaseForm)
        + '&manufacturer=' + encodeURIComponent(manufacturer)
        + '&prescription=' + encodeURIComponent(prescription) + '&price=' + encodeURIComponent(price)
        + '&availableAmount=' + encodeURIComponent(availableAmount);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                if (req.responseText === "emptyName") {
                    alert("name can't be empty");
                } else if (req.responseText === "invalidPrice") {
                    alert('incorrect price');
                } else if (req.responseText === "invalidAvailableAmount") {
                    alert('incorrect available amount');
                } else {
                    alert('drug added to base');
                    $("#addDrugMenu").modal('hide');
                }
            } else {
                alert("can't add drug");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);

}

function drugMenu() {
    var body = 'command=' + encodeURIComponent("getDrugsInfo");
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                var drugsInfoJson = JSON.parse(req.responseText);
                alert(drugsInfoJson);
                $("#releaseForm").empty();
                $("#manufacturer").empty();
                $.each(drugsInfoJson[0], function (i, releaseForm) {
                    var option = document.createElement("option");
                    $("#releaseForm").append(option);
                    option.append(document.createTextNode(releaseForm.description));
                });
                $.each(drugsInfoJson[1], function (i, manufacturer) {
                    var option = document.createElement("option");
                    $("#manufacturer").append(option);
                    option.append(document.createTextNode(manufacturer.name));
                });
                $("#addDrugMenu").modal();
            } else {
                alert("can't open drug menu");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function deleteUser(id, button) {
    var body = 'command=' + encodeURIComponent("deleteUser") + '&id=' + encodeURIComponent(id);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                alert(req.responseText);

                var table = $('#example').DataTable();
                table
                    .row($(button).parents('tr'))
                    .remove()
                    .draw();
                $('#userDetailsModal').modal('hide');
            } else {
                alert("can't delete user");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function deleteDrug(id, button) {
    var body = 'command=' + encodeURIComponent("deleteDrug") + '&id=' + encodeURIComponent(id);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                if (req.responseText === "error") {
                    alert('something going wrong...');
                } else {
                    alert(req.responseText);
                    var table = $('#example1').DataTable();
                    table
                        .row($(button).parents('tr'))
                        .remove()
                        .draw();
                }
            } else {
                alert("can't delete drug");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function givePrescription(id, button) {
    var description = document.getElementById('inputDescription').value;
    var date = document.getElementById('inputDate').value;
    var body = 'command=' + encodeURIComponent("givePrescription") + '&id=' + encodeURIComponent(id)
        + '&description=' + encodeURIComponent(description) + '&date=' + encodeURIComponent(date);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                if (req.responseText === "true") {
                    alert('prescription was given');
                    $(button).empty().append('Given').removeAttr("onclick");
                    $('#prescriptionDetails').modal('hide');
                } else {
                    alert('incorrect date');
                }
            } else {
                alert("can't give prescription");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function showPrescriptionDetails(id, button) {
    document.getElementById('buttonGive').onclick = function () {
        givePrescription(id, button)
    };
    $('#prescriptionDetails').modal();
}

function showUserDetails(id, button) {
    var body = 'command=' + encodeURIComponent("getUserById") + '&id=' + encodeURIComponent(id);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                var userJson = JSON.parse(req.responseText);
                $("#inputLogin").empty().append(document.createTextNode(userJson.login));
                $("#inputUserName").empty().append(document.createTextNode(userJson.firstName));
                $("#inputLastName").empty().append(document.createTextNode(userJson.lastName));
                $("#inputAddress").empty().append(document.createTextNode(userJson.address));
                $("#inputEmail").empty().append(document.createTextNode(userJson.email));
                $("#inputRole").empty().append(document.createTextNode(userJson.role));
                var deleteButton = document.getElementById('deleteButton');
                deleteButton.onclick = function () {
                    deleteUser(id, button);
                };
                $("#userDetailsModal").modal();
            } else {
                alert("can't show details");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function itemAmountUpdate() {
    var cookie = $.cookie('cart');
    if (cookie != null) {
        var cookieJson = JSON.parse(cookie);
        var amount = cookieJson.length;
        if (amount > 0) {
            $('#itemAmount').empty().append(amount);
        } else {
            $('#itemAmount').empty();
        }
    } else {
        $.cookie('cart', JSON.stringify([]));
        itemAmountUpdate();
    }
}

function startTableWithLocale(id) {
    var lang = $.cookie('lang');
    var locale;
    var body = 'command=' + encodeURIComponent("getTableLocale") + '&lang=' + encodeURIComponent(lang);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                var localeString = req.responseText;
                var locale = JSON.parse(localeString);
                $(id).DataTable({
                    language: locale,
                    "order": [[2, "desc"]]
                });
            } else {
                alert("can't get locale");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function totalUpdate(delta) {
    var totalTag = $('#total');
    if (totalTag === null) {
        return;
    }
    var oldTotal = totalTag.text();
    var newTotal = delta + parseInt(oldTotal);
    totalTag.empty().append(newTotal);
}

function changeUser() {
    var login = $('#inputLogin').text();
    var role = document.getElementById('changeRole').value;
    var body = 'command=' + encodeURIComponent("changeUser")
        + '&login=' + encodeURIComponent(login)
        + '&role=' + encodeURIComponent(role);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                alert("user has been changed");
                $('#userDetailsModal').modal('hide');
            } else {
                alert("can't change the user");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}
function changeDrug(id){
    var name = document.getElementById('inputName').value;
    var releaseForm = document.getElementById('releaseForm').value;
    var manufacturer = document.getElementById('manufacturer').value;
    var prescription = document.getElementById('inputPrescription').value;
    var price = document.getElementById('inputPrice').value;
    var availableAmount = document.getElementById('inputAvailableAmount').value;
    var body = 'command=' + encodeURIComponent("changeDrug")
        + '&id=' + encodeURIComponent(id)
        + '&name=' + encodeURIComponent(name) + '&releaseForm=' + encodeURIComponent(releaseForm)
        + '&manufacturer=' + encodeURIComponent(manufacturer)
        + '&prescription=' + encodeURIComponent(prescription) + '&price=' + encodeURIComponent(price)
        + '&availableAmount=' + encodeURIComponent(availableAmount);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                if (req.responseText === "emptyName") {
                    alert("name can't be empty");
                } else if (req.responseText === "invalidPrice") {
                    alert('incorrect price');
                } else if (req.responseText === "invalidAvailableAmount") {
                    alert('incorrect available amount');
                } else {
                    alert('drug had been changed');
                    $("#addDrugMenu").modal('hide');
                }
            } else {
                alert("can't add drug");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}
function changeDrugMenu(id) {
    drugMenu();
    buttonAdd = $('#addDrugButton');
    buttonChange = document.getElementById('changeDrugButton');
    $(buttonAdd).hide();
    $(buttonChange).show();
    buttonChange.onclick = function () {
        changeDrug(id);
    }
}
function addDrugMenu() {
    drugMenu();
    buttonAdd = $('#addDrugButton');
    buttonChange = $('#changeDrugButton');
    $(buttonAdd).show();
    $(buttonChange).hide();
}