

function submit() {
    
    const contactField = document.querySelector('input#contact')
    const contactErr = document.querySelector('div.textfield.contact .err-msg')
    contactErr.innerHTML = ''
    
    const zipField = document.querySelector('input#zip')
    const zipErr = document.querySelector('div.textfield.zip .err-msg')
    zipErr.innerHTML = ''

    if (!(/^[0-9]*$/.test(contactField.value))) {        
        contactErr.innerHTML = 'Contact Number must only contain numbers.'
        return;
    }
    
    if (!(/^[0-9]*$/.test(zipField.value))) {
        zipErr.innerHTML = 'Zip Code must only contain numbers.'
        return;
    }
    
    redirect()

}

function refresh() {
    console.log(1000);
    window.location.reload()
}

function redirect() {

    const main = document.querySelector('main')
    main.innerHTML = `<h1>XYZ Industries</h1><h2>Application Form</h2><p>Thank you for the response</p><button onclick="refresh()">Submit another response</button>`

}

document.querySelector('form').addEventListener('submit', submit)