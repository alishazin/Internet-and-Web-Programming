
const imgSources = [
    "https://www.fcbarcelona.com/photo-resources/2020/02/24/3f1215ed-07e8-47ef-b2c7-8a519f65b9cd/mini_UP3_20200105_FCB_VIS_View_1a_Empty.jpg?width=1200&height=750",
    "https://www.fcbarcelona.com/fcbarcelona/photo/2022/10/23/95c05ed8-7b4a-4563-87ea-9085f9621385/VIC_1659.jpg",
    "https://www.fcbarcelona.com/photo-resources/2022/11/22/f79428ec-55c3-4dbd-8776-035f5ccc2147/comunicat.jpg?width=1200&height=750",
    "https://cosxeuwlta.cloudimg.io/_outsports-prodweb_/uploads/2024/02/usatsi-21109333-scaled.jpg?auto=format&auto=compress&gravity=smart"
]

function onLoad() {
    
    setTimeInterval()
    setIntervalForImg()

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

function setIntervalForImg() {
    
    let count = 0;
    let length = imgSources.length;
    const element = document.querySelector(".post-box img.post");

    function func() {
        element.src = imgSources[count];
        count = (count + 1) % length;
    }
    
    func()
    setInterval(func, 5000);
}