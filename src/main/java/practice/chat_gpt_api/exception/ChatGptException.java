package practice.chat_gpt_api.exception;

public class ChatGptException extends RuntimeException{

    public ChatGptException(){
        super();
    }

    public ChatGptException(String message){
        super(message);
    }

}
