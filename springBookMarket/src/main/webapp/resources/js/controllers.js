function addToCart(action){
	fetch(action, {
		method: 'PUT',
		headers: {
			'Content-Type' : 'application : json'
		}
	})
	.then(response => {
	        if (response.ok) {
	            alert("도서를 장바구니에 추가했습니다.");
	        } else {
	            alert("추가에 실패했습니다.");
	        }
	    })
/*
	document.addForm.action = action;
	document.addForm.submit();
	alert("도서가 장바구니에 추가되었습니다!");
*/
}
/*
function removeFromCart(action){
	console.log("removeFromCart진입")
	document.removeForm.action = action;
	document.removeForm.submit();
	alert("도서를 장바구니에서 삭제했습니다.")
	window.location.reload();
}
*/
function removeFromCart(action) {
    console.log("removeFromCart 진입");
    fetch(action, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            alert("도서를 장바구니에서 삭제했습니다.");
            window.location.reload(); // 페이지 새로 고침
        } else {
            alert("삭제에 실패했습니다.");
        }
    })
}

function clearCart() {
    console.log("clearCart 진입");
    const cartId = sessionStorage.getItem('cartId'); // 세션에서 cartId를 가져옵니다.
    if (!cartId) {
        alert("카트 ID를 찾을 수 없습니다.");
        return;
    }
    fetch(`/cart/${cartId}`, { // 전체 장바구니 삭제를 위한 URL
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            alert("장바구니가 모두 삭제되었습니다.");
            window.location.reload(); // 페이지 새로 고침
        } else {
            alert("삭제에 실패했습니다.");
        }
    })
}

/*
function clearCart(){
	console.log("clearCart진입")
	document.clearForm.submit();
	window.location.reload();
}
*/