import React from "react";
import { Message } from "semantic-ui-react";

export default function Success({ message }) {
  return (
    <Message success>
      <Message.Header>Success</Message.Header>
      <Message.List>
        <Message.Item>{message}</Message.Item>
      </Message.List>
    </Message>
  );
}
