const stripe = Stripe('pk_test_51OurAwRvFVs26Ms8i38uzLziAXWaDhzq2bkrzwh8mEFrHeUYp6KXgc9cYvLBrlzy9LNf8iJ8YoM94LSyscnMN2rs00nqH6EAlK');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
	stripe.redirectToCheckout({
		sessionId: sessionId
	})
});