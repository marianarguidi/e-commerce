package br.com.senac.projetoecommerce.utils;

import java.util.ArrayList;
import java.util.List;

public class SenacExceptions extends Exception{
    private List<String> messageList =  new ArrayList<>();

    public SenacExceptions(List<String> mensagens){
        this.messageList = mensagens;
    }


    public SenacExceptions(String mensagem){
        super(mensagem);//como se estivese usando o construtor da classe pai
    }

    public List<String> getMessages(){
        if (messageList.isEmpty()){
            messageList.add(super.getMessage());
        }

        return messageList;
    }

    public String getMenssage(){
        if(messageList.isEmpty()){
            return super.getMessage();
        }
        return messageList.toString();
    }
}