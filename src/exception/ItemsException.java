package exception;

public class ItemsException extends Exception {

      //异常信息
        private String message;

        public ItemsException(String message)
        {
            super(message);
            this.message=message;

        }


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

}
