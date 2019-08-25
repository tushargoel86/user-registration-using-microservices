import React from "react";
import { Message } from "semantic-ui-react";

export default function Error({ message }) {
  return (
    <Message negative>
      <Message.Header>Error</Message.Header>
      <Message.List>
        <Message.Item>{message}</Message.Item>
      </Message.List>
    </Message>
  );
}
