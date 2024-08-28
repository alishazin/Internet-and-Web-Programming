function onLoad() {
    
    setTimeInterval()

}

function affixZero(num) {
    if (num < 10) return `0${num}`
    else return num;
}

function setTimeInterval() {
    const time = document.querySelector(".time")
    setInterval(() => {
        const datetime = new Date()
        time.innerText = `${affixZero(datetime.getHours())}:${affixZero(datetime.getMinutes())}:${affixZero(datetime.getSeconds())}`
    }, 1000)
}