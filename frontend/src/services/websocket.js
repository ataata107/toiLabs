import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const socketUrl =
  "http://toilabs-alb-1338067063.us-east-1.elb.amazonaws.com/spring-backend/ws"; //"http://spring-backend:8080/ws"; // not localhost

export function connect(callback) {
  const client = new Client({
    webSocketFactory: () => new SockJS(socketUrl),
    onConnect: () => {
      console.log("Connected to WebSocket");
      client.subscribe("/topic/alerts", (message) => {
        const data = JSON.parse(message.body);
        callback(data);
      });
    },
    debug: (str) => console.log(str),
    onStompError: (err) => console.error(err),
  });

  client.activate();
  return client;
}
