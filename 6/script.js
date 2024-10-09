

function submit() {
    
    const contactField = document.querySelector('input#contact')
    const contactErr = document.querySelector('div.textfield.contact .err-msg')
    
    const zipField = document.querySelector('input#zip')
    const zipErr = document.querySelector('div.textfield.zip .err-msg')

    if (!(/^[0-9]*$/.test(contactField.value))) {        
        contactErr.innerHTML = 'Contact Number must only contain numbers.'
    }

    if (!(/^[0-9]*$/.test(zipField.value))) {
        zipErr.innerHTML = 'Zip Code must only contain numbers.'
    }
    
    redirect()

}

function redirect() {

    const main = document.querySelector('main')
    main.innerHTML = `<h1>XYZ Industries</h1><h2>Application Form</h2><p>Thank you for the response</p><button onclick='() => window.location.reload()'>Submit another response</button>`

}

document.querySelector('form').addEventListener('submit', submit)