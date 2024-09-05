[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/78bAn58e)
# Assignment One:  Building a Simple Web Server in Java

### Introduction to HTTP Requests and Web Servers

In this assignment, you will be introduced to the fundamentals of HTTP (Hypertext Transfer Protocol),client server applications, browsers, and web servers by implementing a basic web server in Java. Understanding how web servers handle HTTP requests and serve static files will improve your understanding of the HTTP protocol and the operation of web sites and applications.

#### Learning Objectives

1. **Understanding HTTP Protocol:**
   - Learn the basics of HTTP requests and responses.
   - Explain the significance of different HTTP methods (GET, POST, etc.).
   - Increase your knowledge and understanding of network protocols.

2. **Building a Web Server in Java:**
   - Gain hands-on experience in creating a simple web server as an example of TCP/IP Client-Server applications.
   - Implement functionality for handling HTTP GET requests.
   - Design a web server that must adhere to the existing HTTP/1.1 protocol.
   - Learn how web servers serve static files like HTML, CSS, and images to browser clients.
   - Learn how the HTTP Request-Response affects the design and operation of web applications. 

### Instructions

Create a **Java HTTP Server** that listens on **port 8080**. Implement a basic HHTP server that can accept incoming connections from browsers and handle **HTTP Get** requests.  Your server should process the HTTP Request (request headers) and send a proper HTTP response with the appropriate response headers and the requested file (if available). The **document root** for storing your and serving your files on the web is the **docroot directory** located in your starter files.

The starter files, **Server.java**, implements a simple echo server.  This provides the basic framework for creating your web server. Here is the [rubric](rubric.md) that will be used for grading the assignment.

**Refer to the [Mozilla Developer Network MDN HTTP Documentation](https://developer.mozilla.org/en-US/docs/Web/HTTP) and the [HTTP Wikipedia page](https://en.wikipedia.org/wiki/HTTP) for HTTP/1.1 Request and Response headers and formatting guidelines.**

