function deleteImage(imageId) {
    if (confirm("本当に削除してもよろしいですか？")) {
		const csrfToken = document.querySelector('meta[name="_csrf"]').content;
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
        fetch(`/images/${imageId}/delete`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken,
            },
        })
        .then(response => {
            if (response.ok) {
                // 画像削除が成功した場合、DOMから画像を削除
                document.querySelector(`[data-image-id="${imageId}"]`).remove();
            } else {
                alert("画像の削除に失敗しました。");
            }
        })
        .catch(error => console.error("Error:", error));
    }
}
