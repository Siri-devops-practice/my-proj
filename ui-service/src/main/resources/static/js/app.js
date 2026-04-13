// API Base URLs
const MESSAGE_API = 'http://localhost:8081/api/messages';
const REACTION_API = 'http://localhost:8082/api/reactions';

// DOM Elements
const sorryBox = document.getElementById('sorryBox');
const missBox = document.getElementById('missBox');
const sorryMessage = document.getElementById('sorryMessage');
const missMessage = document.getElementById('missMessage');

// Event Listeners
sorryBox.addEventListener('click', showSorryMessage);
missBox.addEventListener('click', showMissYouMessage);

// Click outside to close popup
document.addEventListener('click', (e) => {
    if (!e.target.closest('.message-popup') && !e.target.closest('.message-box')) {
        closeAllPopups();
    }
});

// Keyboard escape to close
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
        closeAllPopups();
    }
});

async function showSorryMessage() {
    try {
        // Fetch message from service
        const response = await fetch(`${MESSAGE_API}/sorry`);
        const data = await response.json();
        
        // Record user reaction
        await fetch(`${REACTION_API}/record?messageType=SORRY&reactionType=CLICKED`, {
            method: 'POST'
        });

        // Show popup
        sorryMessage.classList.remove('hidden');
        document.body.classList.add('popup-active');

        // Auto-close after 3 seconds
        setTimeout(() => {
            sorryMessage.classList.add('hidden');
            document.body.classList.remove('popup-active');
        }, 3000);

    } catch (error) {
        console.error('Error:', error);
    }
}

async function showMissYouMessage() {
    try {
        // Fetch message from service
        const response = await fetch(`${MESSAGE_API}/miss-you`);
        const data = await response.json();
        
        // Record user reaction
        await fetch(`${REACTION_API}/record?messageType=MISS_YOU&reactionType=CLICKED`, {
            method: 'POST'
        });

        // Show popup
        missMessage.classList.remove('hidden');
        document.body.classList.add('popup-active');

        // Auto-close after 3 seconds
        setTimeout(() => {
            missMessage.classList.add('hidden');
            document.body.classList.remove('popup-active');
        }, 3000);

    } catch (error) {
        console.error('Error:', error);
    }
}

function closeAllPopups() {
    sorryMessage.classList.add('hidden');
    missMessage.classList.add('hidden');
    document.body.classList.remove('popup-active');
}

async function updateStats() {
 