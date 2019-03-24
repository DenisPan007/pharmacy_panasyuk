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

function showSelectedDrug(drugList) {

    var trHTML = '';
    $('#tbodyTagModal').empty();
    $.each(drugList, function (i, item) {
        trHTML = '<tr><td>' + item.drug.name + '</td><td>'
            + item.drug.releaseForm.description + '</td><td>'
            + item.drug.manufacturer.name + '</td><td>'
            + item.drug.isPrescriptionRequired + '</td><td>'
            + '<div class="col-12 m-0">'
            + '<input type="number" class="count form-control"  value="1" max="100" min="1" >'
            + '</div>' + '</td><td>'
            + item.drug.price + '</td><td>'
            + '<button class="btn btn-primary ">Add to cart</button>' + '</td></tr>';
        $('#tbodyTagModal').append(trHTML);
        var tbodyTagModal = document.getElementById('tbodyTagModal');

        var trButtonTag = tbodyTagModal.lastChild;
        var thButtonTag = trButtonTag.lastChild;
        var buttonTag = thButtonTag.lastChild;
        var newCookieJson;
        buttonTag.onclick = function () {
            var inputTag = thButtonTag.previousSibling.previousSibling.lastChild.lastChild;
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
            var tbodyTag = $('#tbodyTagCart');
            if (tbodyTag !== null && trigger) {
                buttonGetPrescription = '<button class="btn btn-primary" >Get</button>' + '</td><td>';
                notRequired = 'Not required' + '</td><td>';
                trHTML = '<tr><td>' + item.drug.name + '</td><td>'
                    + item.drug.releaseForm.description + '</td><td>'
                    + item.drug.manufacturer.name + '</td><td>'
                    + (item.drug.isPrescriptionRequired ? buttonGetPrescription : notRequired)
                    + item.amount + '</td><td>'
                    + item.drug.price + '</td><td>'
                    + '<button class="btn btn-primary" >Delete</button>' + '</td></tr>';
                tbodyTag.append(trHTML);
                tbodyTag = document.getElementById('tbodyTagCart');
                var trJustAdd = tbodyTag.lastChild;
                var tdButtonDeleteTag = trJustAdd.lastChild;
                var buttonDeleteTag = tdButtonDeleteTag.lastChild;
                buttonDeleteTag.onclick = function () {
                    deleteDrugFromCart(item.drug.id, buttonDeleteTag);
                    itemAmountUpdate();
                };
                if (item.drug.isPrescriptionRequired) {
                    var tdButtonGetTag = trJustAdd.lastChild.previousSibling.previousSibling.previousSibling;
                    var buttonGetTag = tdButtonGetTag.lastChild;
                    buttonGetTag.onclick = function () {
                        getPrescription(item.drug.id, buttonGetTag);
                    }
                }
            }
            itemAmountUpdate();
            $("#myModal").modal('hide');
        };

    });
    $("#myModal").modal();
}

function getDrugsByName(drugName) {
    var body = 'command=' + encodeURIComponent("getDrugsByName") + '&name=' + encodeURIComponent(drugName);
    var req = getXMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                var jsonText = req.responseText;
                var json = JSON.parse(jsonText);
                showSelectedDrug(json);
            } else {
                alert("can't get drugs by name");
            }
        }
    };
    req.open('POST', '/pharmacy/ajax', true);
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
    var cookieJson = JSON.parse(cookie);
    var amount= cookieJson.length;
    if(amount > 0) {
        $('#itemAmount').empty().append(amount);
    }
    else{
        $('#itemAmount').empty();
    }
}