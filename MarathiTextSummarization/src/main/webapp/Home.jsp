<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Text Summarizer</title>
    <meta charset="UTF-8">
    <style>
        body {
            background-image: url(https://i.pinimg.com/736x/cc/74/52/cc74520f635e8f75b9a8fc70b0daf09b.jpg);
            background-size: cover;
            background-position: cover;
            width: 100%;
            height: auto;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: 'Arial', sans-serif;
            color: #333;
        }

        .container {
            padding: 20px;
            border-radius: 10px;
            box-shadow: 7px 7px 20px rgba(0, 0, 0, 0.9);
            width: 40%;
            max-width: 600px;
            text-align: center;
        }
        
        h1 {
            font-size: 2.5em;
            color: #333;
            margin-bottom: 20px;
            font-family: 'Georgia', serif;
        }
        
        label {
            font-size: 1.2em;
            font-weight: bold;
            color: #333;
            display: block;
            margin-bottom: 10px;
        }
        
        textarea {
            width: 100%;
            padding: 15px;
            border: 2px solid #000000;
            border-radius: 5px;
            box-sizing: border-box;
            background-color: rgba(255, 255, 255, 0.3);
            resize: none;
            font-size: 1em;
            font-family: 'Courier New', monospace;
            margin-bottom: 20px;
        }
        
        input[type="submit"] {
            width: 50%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #333;
            color: white;
            font-size: 1.2em;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        
        input[type="submit"]:hover {
            background-color: #333;
        }

        .loader {
            border: 8px solid #f3f3f3;
            border-radius: 50%;
            border-top: 8px solid #ff6f00;
            width: 70px;
            height: 70px;
            animation: spin 2s linear infinite;
            display: none;
            position: absolute;
            top: 47%;
            left: 47%;
            transform: translate(-50%, -50%);
        }
        
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
        
    </style>
</head>
<body>
<div class="container">
    <h1>सरळ मराठी</h1>
    <form action="summary" method="post">
        <label for="words">Enter Text</label>
        <textarea id="words" name="words" rows="10" cols="50"></textarea>
        <div class="loader" id="loader"></div>
        <input type="submit" id="submitBtn" value="Create Summary">
    </form>
</div>
</body>
<script>
    window.onload = function() {
        var loader = document.getElementById("loader");
        loader.style.display = "none"; // Hide loader when page is loaded
         
        document.getElementById("submitBtn").onclick = function() {
            loader.style.display = "block"; // Show loader when button is clicked
        };
        
        // Hide loader if user navigates back to this page
        window.onpageshow = function(event) {
            if (event.persisted) {
                loader.style.display = "none";
            }
        };
    };
</script>

</html>
