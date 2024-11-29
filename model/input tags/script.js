console.log(100)

document.querySelector('form#f_text').onsubmit = (e) => {
    e.preventDefault()

    const input = document.querySelector("input#fname")
    console.log(input.value);
}

document.querySelector('form#f_range').onsubmit = (e) => {
    e.preventDefault()

    const input = document.querySelector("input#points")
    console.log(input.value);
}

document.querySelector('form#f_date').onsubmit = (e) => {
    e.preventDefault()

    const input = document.querySelector("input#date1")
    console.log(input.value, new Date(input.value));
}

document.querySelector('form#f_datetime').onsubmit = (e) => {
    e.preventDefault()

    const input = document.querySelector("input#date2")
    console.log(input.value, new Date(input.value));
}

document.querySelector('form#f_month').onsubmit = (e) => {
    e.preventDefault()

    const input = document.querySelector("input#month")
    console.log(input.value, new Date(input.value));
}

document.querySelector('form#f_time').onsubmit = (e) => {
    e.preventDefault()

    const input = document.querySelector("input#time")
    console.log(input.value, new Date(input.value));
}

document.querySelector('form#f_checkbox').onsubmit = (e) => {
    e.preventDefault()

    const input = document.querySelector("input#like_bike")
    console.log(input.checked);
}

document.querySelector('form#f_select').onsubmit = (e) => {
    e.preventDefault()

    const input = document.querySelector("select#team")
    console.log(input.value);
}

document.querySelector('form#f_radio').onsubmit = (e) => {
    e.preventDefault()

    const selected = document.querySelector('input[type="radio"][name="team_r"]:checked')
    console.log(selected.value);
}
