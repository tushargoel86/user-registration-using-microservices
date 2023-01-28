module user.registration {
	exports main.java.com.tushar.usermanagement;
	exports main.java.com.tushar.usermanagement.service;
	exports main.java.com.tushar.usermanagement.exception;
	exports main.java.com.tushar.usermanagement.request;
	exports main.java.com.tushar.usermanagement.repository;
	exports main.java.com.tushar.usermanagement.message;
	exports main.java.com.tushar.usermanagement.model;
	exports main.java.com.tushar.usermanagement.resources;
	exports main.java.com.tushar.usermanagement.config;

	requires com.fasterxml.jackson.databind;
	requires jackson.annotations;
	requires java.validation;
	requires mongo.java.driver;
	requires spring.amqp;
	requires spring.beans;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.data.commons;
	requires spring.data.mongodb;
	requires spring.rabbit;
	requires spring.web;
}