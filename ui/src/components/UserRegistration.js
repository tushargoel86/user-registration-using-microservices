import React, { useState } from "react";
import { Form, Grid, Container, Button } from "semantic-ui-react";
import Error from "./Error";
import Success from "./Success";

import { URL } from "../constants/Constants";

export function registerEmail(email, password, setRes) {
  fetch(URL + "register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email: email, password: password })
  })
    .then(resp => resp.json())
    .then(res => {
      if (res.status === 201) {
        setRes({ errorMessage: "", successMessage: res.message });
      } else {
        setRes({ successMessage: "", errorMessage: res.message });
      }
    })
    .catch(err => setRes({ successMessage: "", errorMessage: err.message }));
}

export default function UserRegistration() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [res, setRes] = useState({ errorMessage: "", successMessage: "" });

  function handleSubmit(e) {
    e.preventDefault();
    registerEmail(email, password, setRes);
  }

  return (
    <Container style={{ marginTop: 100 }}>
      <Grid columns={2}>
        <Grid.Row floated="centered">
          <Grid.Column>
            <Form onSubmit={handleSubmit}>
              <Form.Input
                label="Email address"
                name="email"
                onChange={e => setEmail(e.target.value)}
                value={email}
              />
              <Form.Input
                label="Password"
                name="password"
                onChange={e => setPassword(e.target.value)}
                value={password}
              />
              <Button type="submit">Submit</Button>
            </Form>
            {res.errorMessage ? <Error message={res.errorMessage} /> : null}
            {res.successMessage ? (
              <Success message={res.successMessage} />
            ) : null}
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </Container>
  );
}

