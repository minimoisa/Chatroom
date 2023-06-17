const chatroomList = document.getElementById('chatroom-list');
const newChatroomForm = document.getElementById('new-chatroom-form');
const chatroomNameInput = document.getElementById('chatroom-name');
const chatroomTitle = document.getElementById('chatroom-title');
const messageList = document.getElementById('message-list');
const messageForm = document.getElementById('message-form');
const messageInput = document.getElementById('message-input');

function fetchChatrooms() {
    fetch('/chatroos')
        .then(response => response.json())
        .then(chatrooms => {
            chatroomList.innerHTML = '';
            chatrooms.forEach(chatroom => {
                const li = document.createElement('li');
                li.textContent = chatroom.name;
                li.addEventListener('click', () => joinChatroom(chatroom.id));
                chatroomList.appendChild(li);
            });
        })
        .catch(error => console.error('Error:', error));
}


function createChatroom(event) {
    event.preventDefault();

    const chatroomName = chatroomNameInput.value;
    if (chatroomName.trim() === '') {
        return;
    }

    fetch('/chatrooms', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: chatroomName })
    })
        .then(response => response.json())
        .then(chatroom => {
            joinChatroom(chatroom.id);
            chatroomNameInput.value = '';
        })
        .catch(error => console.error('Error:', error));
}


function joinChatroom(chatroomId) {
    fetch(`/chatrooms/${chatroomId}`)
        .then(response => response.json())
        .then(chatroom => {
            chatroomTitle.textContent = chatroom.name;
            messageList.innerHTML = '';
            chatroom.messages.forEach(message => {
                const li = document.createElement('li');
                li.textContent = `${message.user.username}: ${message.content}`;
                messageList.appendChild(li);
            });
        })
        .catch(error => console.error('Error:', error));
}


function sendMessage(event) {
    event.preventDefault();

    const message = messageInput.value;
    if (message.trim() === '') {
        return;
    }

    fetch('/messages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ content: message })
    })
        .then(response => {
            if (response.ok) {
                messageInput.value = '';
            } else {
                alert('Failed to send message. Please try again.');
            }
        })
        .catch(error => console.error('Error:', error));
}


document.addEventListener('DOMContentLoaded', fetchChatrooms);
newChatroomForm.addEventListener('submit', createChatroom);
messageForm.addEventListener('submit', sendMessage);