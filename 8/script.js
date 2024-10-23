
var submitted = false;
var questions = [];
var options = [];
var correctOptionNames = [];
var quizEndAt = null;

function shuffle(unshuffled) {
    let shuffled = unshuffled
    .map(value => ({ value, sort: Math.random() }))
    .sort((a, b) => a.sort - b.sort)
    .map(({ value }) => value)
    return shuffled
}

function affixZero(num) {
    if (num < 10) return `0${num}`
    else return num;
}

function refresh() {
    window.location.reload()
}

function loadCategoryOptions() {
    const optionsContainer = document.querySelector('form.options-container')
    const loadingBox = document.querySelector('div.loading-box')
    const select = document.querySelector('#category')
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            loadingBox.style.display = 'none' 
            optionsContainer.style.display = 'block' 
        
            const parsedRes = JSON.parse(this.responseText)
            
            parsedRes.trivia_categories.forEach(element => {
                const option = document.createElement('option')
                option.value = element.id
                option.innerText = element.name
                select.appendChild(option)
            });

        }
    };
    xhttp.open("GET", "https://opentdb.com/api_category.php", true);
    xhttp.send();

}

function submitOptions() {

    const questionContainer = document.querySelector('form.questions-container')
    const optionsContainer = document.querySelector('form.options-container')
    const amountNumber = document.querySelector("input#amount")
    const categoryDropdown = document.querySelector("select#category")
    const difficultyDropdown = document.querySelector("select#difficulty")

    let url = 'https://opentdb.com/api.php?type=multiple'

    url += `&amount=${amountNumber.value}`
    if (categoryDropdown.value != 'any') {
        url += `&category=${categoryDropdown.value}`
    }
    if (difficultyDropdown.value != 'any') {
        url += `&difficulty=${difficultyDropdown.value}`
    }
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            optionsContainer.style.display = 'none'
            questionContainer.style.display = 'block'

            const parsedRes = JSON.parse(this.responseText)

            let html = "<div id='timer'>Time left: <span>00:00</span></div><div class='progress-bar'><div class='inner'></div></div>";
            const totalTime = (parsedRes.results.length * 20000)
            quizEndAt = new Date().getTime() + totalTime

            const timerInterval = setInterval(() => {
                const msRemaining = quizEndAt - new Date().getTime()
                if (msRemaining < 0) {
                    clearInterval(timerInterval)
                    if (submitted === false)
                        quizSubmit(1)
                } else {
                    if (submitted === true) clearInterval(timerInterval)
                    else {
                        document.querySelector('div#timer span').innerHTML = `${affixZero(Math.floor(msRemaining / 60000))}:${affixZero(Math.floor((msRemaining % 60000) / 1000))}`
                        document.querySelector('.progress-bar .inner').style.width = `${(msRemaining/totalTime) * 100}%`
                        if (msRemaining < (25 / 100) * totalTime) {
                            document.querySelector('.progress-bar .inner').style.backgroundColor = 'red';
                        }
                    }
                }
            }, 1000)
            
            parsedRes.results.forEach((element, _index) => {

                questions.push(element.question)
                
                html += `
                <div class="qn-box">
                    <div class="question">${_index + 1}. ${element.question}</div>
                    <select name="q${_index+1}" id="q${_index+1}" required>
                    <option disabled selected value> -- select an option -- </option>
                `
                
                options = [element.correct_answer, ...element.incorrect_answers]
                options = shuffle(options)
                correctOptionNames.push(element.correct_answer)

                options.forEach((optionValue) => {
                    html += `
                    <option value="${optionValue}">${optionValue}</option>
                    `
                })
                
                html += ` </select></div>`
            });

            html += '</div><button id="submit-btn">Submit</button>'

            questionContainer.innerHTML = html

        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
    
}

function quizSubmit(instance) {

    submitted = true;

    const questionContainer = document.querySelector('form.questions-container')
    const resultContainer = document.querySelector('div.result-container')
    questionContainer.style.display = 'none'
    resultContainer.style.display = 'block'

    let html = ''
    let points = 0

    for (let i=0; i<correctOptionNames.length; i++) {

        const dropdown = document.querySelector(`select#q${i+1}`)
        if (dropdown.value === correctOptionNames[i]) {
            points++
        }

        html += `<div class="single-container">
            <div class="question">${i+1}. ${questions[i]}</div>
            <div class="answer-container">
                <div class="your-ans" style="color:${dropdown.value === correctOptionNames[i] ? 'green' : 'red'};">Your answer: ${dropdown.value}</div>
                <div class="crct-ans" style="color:green;">Correct answer: ${correctOptionNames[i]}</div>
            </div>
        </div>`

    }

    html += `<div class='points'>${instance === 1 ? 'You ran out of time!<br/>' : ''}You scored ${points} out of ${questions.length}</div><br/><br/><button onclick='refresh()'>New Quiz</button>`

    resultContainer.innerHTML = html;

}

loadCategoryOptions()
document.querySelector('form.options-container').addEventListener('submit', submitOptions)
document.querySelector('form.questions-container').addEventListener('submit', () => quizSubmit(0))