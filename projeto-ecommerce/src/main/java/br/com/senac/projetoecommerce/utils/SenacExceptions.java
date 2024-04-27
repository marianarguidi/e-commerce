package br.com.senac.projetoecommerce.utils;

import java.util.ArrayList;
import java.util.List;

public class SenacExceptions extends Exception{

        private List<String> messageList = new ArrayList<>();

        public SenacExceptions(String mensagem){
            super(mensagem);
        }

        public SenacExceptions(List<String> mensagens){
            this.messageList = mensagens;
        }

        public List<String> getMessagens(){
            if (messageList.isEmpty()){
                messageList.add(super.getMessage());
            }

            return messageList;
        }

        public String getMessage(){
            if (messageList.isEmpty()){
                return super.getMessage();
            }
            return messageList.toString();
        }

    }
