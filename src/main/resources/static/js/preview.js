const imageInput = document.getElementById('imageFiles');
const imagePreview = document.getElementById('imagePreview');

imageInput.addEventListener('change', () => {
	imagePreview.innerHTML = '';
	
	if(imageInput.files.length > 0){
		Array.from(imageInput.files).forEach(file => {
			let fileReader = new FileReader();
			fileReader.onload = () => {
				imagePreview.innerHTML += `<img src="${fileReader.result}" class="mb-3">`
			}
			fileReader.readAsDataURL(file);
		});
	}
})
