const sidebar = document.getElementById('sidebar');
const button = document.getElementById('toggle');

button.addEventListener('click', _ => {
	sidebar.classList.toggle('collapsed');
});