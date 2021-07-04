public class PaymentGateway {

    public String pay(String amount, String account, String bankName) {
        // do some business logic

        String status = BankFactory.getBankFromBankName(bankName).makePayment(amount, account);
        //do some login
        return status;
    }

//    private String payWithHdfc(int amount, int account) {
//        // do api call here
//        //Return status
//        return "Success";
//    }

}


interface IBank{
    String makePayment(String amount, String account);

}

class HDFC implements  IBank{

    @Override
    public String makePayment(String amount, String account) {
        return null;
    }
}

class AXIS implements IBank{
    @Override
    public String makePayment(String amount, String account) {
        return null;
    }
}

class ICICI implements IBank{
    //api
    @Override
    public String makePayment(String amount, String account) {
        return null;
    }
}


class BankFactory{

    public static IBank getBankFromBankName(String bankName){
        // return bank
    }
}
