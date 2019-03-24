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

function addItemToCart(button,itemList,index){
    var item  = JSON.parse(itemList)[index];
    var id = item.drug.id;
var inputTag = document.getElementById(id);
    var amount = inputTag.value;
    if (amount <= 0) {
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
            if (cookieCartJson[i].drug.id === item.drug.id) {
                trigger = false;
                cookieCartJson[i]=item;
            }
        }
        if (trigger) {
            cookieCartJson.push(item);
        }
        newCookieJson = cookieCartJson;
    } else {
        newCookieJson = [];
        newCookieJson.push(item);
    }
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
            cookieCartJson.splice(i, 1);
        }
    }

    var newCookieString = JSON.stringify(cookieCartJson);
    $.cookie('cart', newCookieString);
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
                alert('your query has been made ' + req.responseText);
                $(button).removeAttr("onclick");
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
                var parent = button.parentElement;
                parent.removeChild(button);
                parent.append('Confirmed');
            } else {
                alert("can't confirm");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);
}

function addDrugToBase() {
    var name = document.getElementById('inputName').value;
    var releaseForm = document.getElementById('releaseForm').value;
    var manufacturer = document.getElementById('manufacturer').value;
    var prescription = document.getElementById('inputPrescription').value;
    var price = document.getElementById('inputPrice').value;
    var body = 'command=' + encodeURIComponent("addDrug")
        + '&name=' + encodeURIComponent(name) + '&releaseForm=' + encodeURIComponent(releaseForm)
        + '&manufacturer=' + encodeURIComponent(manufacturer)
        + '&prescription=' + encodeURIComponent(prescription) + '&price=' + encodeURIComponent(price);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                if (req.responseText === "emptyName") {
                    alert("name can't be empty");
                }
                else if(req.responseText === "invalidPrice"){
                    alert('incorrect price');
                }
                else {
                    alert('drug added to base');
                }
            } else {
                alert("can'not add drug");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(body);

}

function addDrugMenu() {
    var body = 'command=' + encodeURIComponent("getDrugsInfo");
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                var drugsInfoJson = JSON.parse(req.responseText);
                $.each(drugsInfoJson[0], function (i, releaseForm) {
                    $("#releaseForm").append('<option>' + releaseForm.description + '</option>');
                });
                $.each(drugsInfoJson[1], function (i, manufacturer) {
                    $("#manufacturer").append('<option>' + manufacturer.name + '</option>');
                });
                $("#addDrugMenu").modal();
            } else {
                alert("can'not delete user");
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
            } else {
                alert("can'not delete user");
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
                if(req.responseText==="error"){
                    alert('something going wrong...sorry');
                }
                else {
                    alert(req.responseText);
                    var table = $('#example1').DataTable();
                    table
                        .row($(button).parents('tr'))
                        .remove()
                        .draw();
                }
            } else {
                alert("can'not delete drug");
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
                    var parent = button.parentElement;
                    parent.removeChild(button);
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
    }
    else {
        $.cookie('cart', JSON.stringify([]));
        itemAmountUpdate();
    }
}