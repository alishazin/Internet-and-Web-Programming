console.log("Hello World in Console")

const string = "HELLO WORLD";

for (let i=0; i<string.length; i++) {
	setTimeout(() => {
		const span = document.createElement('span');
		span.innerText = string[i];
		document.querySelector('h1').appendChild(span);
		
		if (i == string.length - 1) {
			setTimeout(() => {
				document.querySelector('#cursor').remove();
			}, 3000);
		}
	}, i * 1000);		
}