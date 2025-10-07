//package solidPrinciples.AllInOne;
//
//import solidPrinciples.AllInOne.AccountRepository;
//import solidPrinciples.AllInOne.DIP.ConsoleNotificationService;
//import solidPrinciples.AllInOne.DIP.InMemoryAccountRepository;
//import solidPrinciples.AllInOne.DIP.NotificationService;
//import solidPrinciples.AllInOne.DIP.impl.BankingService;
//import solidPrinciples.AllInOne.ISP.InterestEarning;
//import solidPrinciples.AllInOne.ISP.Transferable;
//import solidPrinciples.AllInOne.ISP.impl.PremiumSavingsAccount;
//import solidPrinciples.AllInOne.OCP.Account;
//import solidPrinciples.AllInOne.OCP.impl.CheckingAccount;
//import solidPrinciples.AllInOne.OCP.impl.SavingsAccount;
//
//import java.math.BigDecimal;
//import java.util.List;
//
///*
// * @created 07/10/2025 -00:15
// * @project CoreJavaRepo
// * @author  bhartisingh
// */public class SolidBankingSystem {
//
//    public static void main(String[] args) {
//        try {
//            // Setup dependencies
//            AccountRepository accountRepository = new InMemoryAccountRepository();
//            NotificationService notificationService = new ConsoleNotificationService();
//            BankingService bankingService = new BankingService(accountRepository, notificationService);
//
//            // Create different types of accounts (OCP)
//            Account savings = new SavingsAccount("SAV001", new BigDecimal("1000"), new BigDecimal("100"));
//            Account checking = new CheckingAccount("CHK001", new BigDecimal("500"), new BigDecimal("200"));
//            Account premium = new PremiumSavingsAccount("PRE001", new BigDecimal("2000"), new BigDecimal("0.05"));
//
//            // Save accounts
//            accountRepository.save(savings);
//            accountRepository.save(checking);
//            accountRepository.save(premium);
//
//            System.out.println("=== Initial Balances ===");
//            displayBalance(savings);
//            displayBalance(checking);
//            displayBalance(premium);
//
//            // Process transactions (SRP)
//            System.out.println("\n=== Processing Transactions ===");
//            bankingService.processDeposit(savings, new BigDecimal("200"));
//            bankingService.processWithdrawal(checking, new BigDecimal("100"));
//
//            // Transfer (ISP - only premium account supports transfers)
//            if (premium instanceof Transferable) {
//                bankingService.processTransfer(premium, savings, new BigDecimal("300"));
//            }
//
//            // Apply interest (ISP - only premium account has this feature)
//            if (premium instanceof InterestEarning) {
//                ((InterestEarning) premium).applyInterest();
//            }
//
//            System.out.println("\n=== Final Balances ===");
//            displayBalance(savings);
//            displayBalance(checking);
//            displayBalance(premium);
//
//            // Demonstrate LSP - all accounts can be treated uniformly
//            System.out.println("\n=== All Accounts (LSP Demonstration) ===");
//            List<Account> allAccounts = accountRepository.findAll();
//            for (Account account : allAccounts) {
//                System.out.println("Account " + account.getAccountNumber() +
//                        ": $" + account.getBalance());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void displayBalance(Account account) {
//        System.out.println(account.getClass().getSimpleName() +
//                " " + account.getAccountNumber() +
//                ": $" + account.getBalance());
//    }
//}
