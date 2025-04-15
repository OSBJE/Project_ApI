

document.getElementById("send-btn").addEventListener("click", async () => {
    const prompt = document.getElementById("user-input").value;
    const responseBox = document.getElementById("response-box");

    responseBox.innerHTML = "Thinking...";

    // Mock call (replace this with your fetch to backend)
    setTimeout(() => {
        responseBox.innerHTML = "ChatGPT says: Hello from the AI!";
    }, 1000);
});