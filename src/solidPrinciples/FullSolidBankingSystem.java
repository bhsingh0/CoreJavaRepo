package solidPrinciples;

/*
 * @created 07/10/2025 -00:43
 * @project CoreJavaRepo
 * @author  bhartisingh
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// ========== SRP (Single Responsibility Principle) ==========

// Each class has a single responsibility
interface Transaction {
    BigDecimal getAmount();
    LocalDateTime getTimestamp();
    String getType();
}

class DepositTransaction implements Transaction {
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public DepositTransaction(BigDecimal amount) {
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    @Override public BigDecimal getAmount() { return amount; }
    @Override public LocalDateTime getTimestamp() { return timestamp; }
    @Override public String getType() { return "DEPOSIT"; }
}

class WithdrawalTransaction implements Transaction {
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public WithdrawalTransaction(BigDecimal amount) {
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    @Override public BigDecimal getAmount() { return amount; }
    @Override public LocalDateTime getTimestamp() { return timestamp; }
    @Override public String getType() { return "WITHDRAWAL"; }
}

// ========== OCP (Open/Closed Principle) ==========

// Open for extension, closed for modification
interface Account {
    String getAccountNumber();
    BigDecimal getBalance();
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount) throws InsufficientFundsException;
    List<Transaction> getTransactionHistory();
}

abstract class BaseAccount implements Account {
    protected final String accountNumber;
    protected BigDecimal balance;
    protected final List<Transaction> transactions;

    public BaseAccount(String accountNumber, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    @Override public String getAccountNumber() { return accountNumber; }
    @Override public BigDecimal getBalance() { return balance; }
    @Override public List<Transaction> getTransactionHistory() { return new ArrayList<>(transactions); }

    @Override
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        transactions.add(new DepositTransaction(amount));
    }
}

// Extendable account types without modifying existing code
class SavingsAccount extends BaseAccount {
    private final BigDecimal minimumBalance;

    public SavingsAccount(String accountNumber, BigDecimal initialBalance, BigDecimal minimumBalance) {
        super(accountNumber, initialBalance);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (balance.subtract(amount).compareTo(minimumBalance) < 0) {
            throw new InsufficientFundsException("Cannot go below minimum balance: " + minimumBalance);
        }
        balance = balance.subtract(amount);
        transactions.add(new WithdrawalTransaction(amount));
    }
}

class CheckingAccount extends BaseAccount {
    private final BigDecimal overdraftLimit;

    public CheckingAccount(String accountNumber, BigDecimal initialBalance, BigDecimal overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (balance.subtract(amount).compareTo(overdraftLimit.negate()) < 0) {
            throw new InsufficientFundsException("Exceeds overdraft limit: " + overdraftLimit);
        }
        balance = balance.subtract(amount);
        transactions.add(new WithdrawalTransaction(amount));
    }
}

// ========== LSP (Liskov Substitution Principle) ==========

// All account types can be substituted for their base type
class InterestBearingAccount extends BaseAccount {
    private final BigDecimal interestRate;

    public InterestBearingAccount(String accountNumber, BigDecimal initialBalance, BigDecimal interestRate) {
        super(accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance = balance.subtract(amount);
        transactions.add(new WithdrawalTransaction(amount));
    }

    public void applyInterest() {
        BigDecimal interest = balance.multiply(interestRate);
        deposit(interest);
    }
}

// ========== ISP (Interface Segregation Principle) ==========

// Segregated interfaces for different behaviors
interface Transferable {
    void transferTo(Account target, BigDecimal amount) throws InsufficientFundsException;
}

interface Loanable {
    void applyForLoan(BigDecimal amount);
    void repayLoan(BigDecimal amount);
}

interface InterestEarning {
    void applyInterest();
}

// Classes implement only interfaces they need
class PremiumSavingsAccount extends BaseAccount implements Transferable, InterestEarning {
    private final BigDecimal interestRate;

    public PremiumSavingsAccount(String accountNumber, BigDecimal initialBalance, BigDecimal interestRate) {
        super(accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance = balance.subtract(amount);
        transactions.add(new WithdrawalTransaction(amount));
    }

    @Override
    public void transferTo(Account target, BigDecimal amount) throws InsufficientFundsException {
        this.withdraw(amount);
        target.deposit(amount);
    }

    @Override
    public void applyInterest() {
        BigDecimal interest = balance.multiply(interestRate);
        deposit(interest);
    }
}

// ========== DIP (Dependency Inversion Principle) ==========

// High-level modules depend on abstractions
interface AccountRepository {
    void save(Account account);
    Account findByNumber(String accountNumber);
    List<Account> findAll();
}

interface TransactionService {
    void processDeposit(Account account, BigDecimal amount);
    void processWithdrawal(Account account, BigDecimal amount) throws InsufficientFundsException;
    void processTransfer(Account from, Account to, BigDecimal amount) throws InsufficientFundsException;
}

// High-level service depends on abstractions
class BankingService implements TransactionService {
    private final AccountRepository accountRepository;
    private final NotificationService notificationService;

    public BankingService(AccountRepository accountRepository, NotificationService notificationService) {
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void processDeposit(Account account, BigDecimal amount) {
        account.deposit(amount);
        accountRepository.save(account);
        notificationService.notifyDeposit(account, amount);
    }

    @Override
    public void processWithdrawal(Account account, BigDecimal amount) throws InsufficientFundsException {
        account.withdraw(amount);
        accountRepository.save(account);
        notificationService.notifyWithdrawal(account, amount);
    }

    @Override
    public void processTransfer(Account from, Account to, BigDecimal amount) throws InsufficientFundsException {
        if (from instanceof Transferable) {
            ((Transferable) from).transferTo(to, amount);
            accountRepository.save(from);
            accountRepository.save(to);
            notificationService.notifyTransfer(from, to, amount);
        } else {
            throw new UnsupportedOperationException("Account does not support transfers");
        }
    }
}

// Abstraction for notification
interface NotificationService {
    void notifyDeposit(Account account, BigDecimal amount);
    void notifyWithdrawal(Account account, BigDecimal amount);
    void notifyTransfer(Account from, Account to, BigDecimal amount);
}

// Concrete implementations
class InMemoryAccountRepository implements AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public void save(Account account) {
        accounts.removeIf(acc -> acc.getAccountNumber().equals(account.getAccountNumber()));
        accounts.add(account);
    }

    @Override
    public Account findByNumber(String accountNumber) {
        return accounts.stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }
}

class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyDeposit(Account account, BigDecimal amount) {
        System.out.println("Deposit notification: " + amount + " to account " + account.getAccountNumber());
    }

    @Override
    public void notifyWithdrawal(Account account, BigDecimal amount) {
        System.out.println("Withdrawal notification: " + amount + " from account " + account.getAccountNumber());
    }

    @Override
    public void notifyTransfer(Account from, Account to, BigDecimal amount) {
        System.out.println("Transfer notification: " + amount + " from " +
                from.getAccountNumber() + " to " + to.getAccountNumber());
    }
}

// ========== Exceptions ==========
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// ========== Demo Class ==========
public class FullSolidBankingSystem {
    public static void main(String[] args) {
        try {
            // Setup dependencies
            AccountRepository accountRepository = new InMemoryAccountRepository();
            NotificationService notificationService = new ConsoleNotificationService();
            BankingService bankingService = new BankingService(accountRepository, notificationService);

            // Create different types of accounts (OCP)
            Account savings = new SavingsAccount("SAV001", new BigDecimal("1000"), new BigDecimal("100"));
            Account checking = new CheckingAccount("CHK001", new BigDecimal("500"), new BigDecimal("200"));
            Account premium = new PremiumSavingsAccount("PRE001", new BigDecimal("2000"), new BigDecimal("0.05"));

            // Save accounts
            accountRepository.save(savings);
            accountRepository.save(checking);
            accountRepository.save(premium);

            System.out.println("=== Initial Balances ===");
            displayBalance(savings);
            displayBalance(checking);
            displayBalance(premium);

            // Process transactions (SRP)
            System.out.println("\n=== Processing Transactions ===");
            bankingService.processDeposit(savings, new BigDecimal("200"));
            bankingService.processWithdrawal(checking, new BigDecimal("100"));

            // Transfer (ISP - only premium account supports transfers)
            if (premium instanceof Transferable) {
                bankingService.processTransfer(premium, savings, new BigDecimal("300"));
            }

            // Apply interest (ISP - only premium account has this feature)
            if (premium instanceof InterestEarning) {
                ((InterestEarning) premium).applyInterest();
            }

            System.out.println("\n=== Final Balances ===");
            displayBalance(savings);
            displayBalance(checking);
            displayBalance(premium);

            // Demonstrate LSP - all accounts can be treated uniformly
            System.out.println("\n=== All Accounts (LSP Demonstration) ===");
            List<Account> allAccounts = accountRepository.findAll();
            for (Account account : allAccounts) {
                System.out.println("Account " + account.getAccountNumber() +
                        ": $" + account.getBalance());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayBalance(Account account) {
        System.out.println(account.getClass().getSimpleName() +
                " " + account.getAccountNumber() +
                ": $" + account.getBalance());
    }
}