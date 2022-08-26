package il.ac.shenkar.costmanager.client.view;

import il.ac.shenkar.costmanager.client.viewmodel.IViewModel;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Cost;
import il.ac.shenkar.costmanager.entities.Currency;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class View implements IView {

    private IViewModel vm;
    private JFrame frame;
    private CardLayout cards, topPanels, rightPanels;
    private JPanel screen,screenMyCosts, screenAddCost, screenAddCategory, screenLogin, screenLogup;
    private JPanel panelTopLogin, panelTopLogup, panelTopLogout, panelTop, panelRightAuthorized, panelRightUnauthorized, panelRight;
    private JButton buttonMyCosts, buttonAddCost, buttonAddCategory, buttonLogin, buttonLogup, buttonLogout;

    public View() {

        // initializing layout
        frame = new JFrame("Cost Manager");
        cards = new CardLayout();
        topPanels = new CardLayout();
        rightPanels = new CardLayout();

        // initializing panels
        screen = new JPanel();
        screenMyCosts = new JPanel();
        screenAddCost = new JPanel();
        screenAddCategory = new JPanel();
        screenLogin = new JPanel();
        screenLogup = new JPanel();
        panelTopLogin = new JPanel();
        panelTopLogup = new JPanel();
        panelTopLogout = new JPanel();
        panelTop = new JPanel();
        panelRightAuthorized = new JPanel();
        panelRightUnauthorized = new JPanel();
        panelRight = new JPanel();

        // initializing buttons
        buttonMyCosts = new JButton("My Costs");
        buttonAddCost = new JButton("Add Cost");
        buttonAddCategory = new JButton("Add Category");
        buttonLogin = new JButton("Login");
        buttonLogup = new JButton("Logup");
        buttonLogout = new JButton("Logout");
    }

    void buildTopPanel() {

        // setting top buttons for each screen
        buttonLogin.setBackground(Color.WHITE);
        buttonLogup.setBackground(Color.WHITE);
        buttonLogout.setBackground(Color.WHITE);

        // setting the logo for each screen
        JLabel logoLogin = new JLabel("Cost Manager");
        JLabel logoLogup = new JLabel("Cost Manager");
        JLabel logoLogout = new JLabel("Cost Manager");

        logoLogin.setForeground(Color.WHITE);
        logoLogup.setForeground(Color.WHITE);
        logoLogout.setForeground(Color.WHITE);

        logoLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
        logoLogup.setFont(new Font("Tahoma", Font.PLAIN, 22));
        logoLogout.setFont(new Font("Tahoma", Font.PLAIN, 22));

        // setting top panel style
        panelTopLogin.setBorder(new EmptyBorder(15, 20, 15, 20));
        panelTopLogup.setBorder(new EmptyBorder(15, 20, 15, 20));
        panelTopLogout.setBorder(new EmptyBorder(15, 20, 15, 20));

        panelTopLogin.setBackground(Color.decode("#38425A"));
        panelTopLogup.setBackground(Color.decode("#38425A"));
        panelTopLogout.setBackground(Color.decode("#38425A"));

        panelTopLogin.setPreferredSize(new Dimension(800, 60));
        panelTopLogup.setPreferredSize(new Dimension(800, 60));
        panelTopLogout.setPreferredSize(new Dimension(800, 60));
        panelTop.setPreferredSize(new Dimension(800, 60));

        // building the top panel
        panelTopLogin.add(logoLogin, BorderLayout.WEST);
        panelTopLogup.add(logoLogup, BorderLayout.WEST);
        panelTopLogout.add(logoLogout, BorderLayout.WEST);
        panelTopLogin.add(buttonLogin, BorderLayout.EAST);
        panelTopLogup.add(buttonLogup, BorderLayout.EAST);
        panelTopLogout.add(buttonLogout, BorderLayout.EAST);

        // handling events
        buttonLogin.addActionListener(e -> {
            cards.show(screen, "Login");
            topPanels.show(panelTop, "TopLogup");
            rightPanels.show(panelRight, "RightUnauthorized");
        });

        buttonLogup.addActionListener(e -> {
            cards.show(screen, "Logup");
            topPanels.show(panelTop, "TopLogin");
            rightPanels.show(panelRight, "RightUnauthorized");
        });

        buttonLogout.addActionListener(e -> {
            cards.show(screen, "Login");
            topPanels.show(panelTop, "TopLogup");
            rightPanels.show(panelRight, "RightUnauthorized");
        });
    }

    void buildRightPanel() {

        // setting right buttons
        buttonMyCosts.setPreferredSize(new Dimension(120, 30));
        buttonAddCost.setPreferredSize(new Dimension(120, 30));
        buttonAddCategory.setPreferredSize(new Dimension(120, 30));

        buttonMyCosts.setBackground(Color.WHITE);
        buttonAddCost.setBackground(Color.WHITE);
        buttonAddCategory.setBackground(Color.WHITE);

        // setting right panel style
        panelRightAuthorized.setBorder(new EmptyBorder(70, 10, 0, 10));
        panelRightUnauthorized.setBorder(new EmptyBorder(70, 10, 0, 10));

        panelRightAuthorized.setBackground(Color.decode("#5E6C8D"));
        panelRightUnauthorized.setBackground(Color.WHITE);

        panelRightAuthorized.setPreferredSize(new Dimension(160, 640));
        panelRightUnauthorized.setPreferredSize(new Dimension(160, 640));
        panelRight.setPreferredSize(new Dimension(160, 640));

        // building the right panel
        panelRightAuthorized.add(buttonMyCosts);
        panelRightAuthorized.add(buttonAddCost);
        panelRightAuthorized.add(buttonAddCategory);

        // handling events
        buttonMyCosts.addActionListener(e -> cards.show(screen, "MyCosts"));
        buttonAddCost.addActionListener(e -> cards.show(screen, "AddCost"));
        buttonAddCategory.addActionListener(e -> cards.show(screen, "AddCategory"));
    }

    void buildMyCostsScreen() {

        // building the costs list
        JPanel costsList = new JPanel();
        costsList.setPreferredSize(new Dimension(800, 480));
        costsList.setBackground(Color.WHITE);

        Cost[] costs = {
                new Cost(null, "123", 20, "123", "Bread, some stuff, different stuff", null),
                new Cost(null, "123", 20, "123", "Bread, some stuff, different stuff", null),
                new Cost(null, "123", 20, "123", "Bread, some stuff, different stuff", null),
                new Cost(null, "123", 20, "123", "Bread, some stuff, different stuff", null),
                new Cost(null, "123", 20, "123", "Bread, some stuff, different stuff", null),
        };

        if (costs.length == 0) {
            JLabel emptyMessage = generateLabel("Empty List", 70, 30);
            costsList.add(emptyMessage);
        }

        for(var cost : costs) {
            JPanel costPanel = new JPanel();
            costPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            costPanel.setBackground(Color.decode("#F3F1F1"));
            costPanel.setPreferredSize(new Dimension(800, 60));
            costPanel.setBorder(new EmptyBorder(10, 5, 10, 5));

            JLabel category = generateLabel("Groceries", 150, 30);
            JLabel sum = generateLabel(String.valueOf(cost.getSum()), 30, 30);
            JLabel currency = generateLabel("USD", 150, 30);
            JLabel description = generateLabel(cost.getDescription(), 270, 30);
            costPanel.add(category);
            costPanel.add(sum);
            costPanel.add(currency);
            costPanel.add(description);

            costsList.add(costPanel);
        }

        // building the MY Costs screen
        screenMyCosts.setBackground(Color.WHITE);
        screenMyCosts.setBorder(new EmptyBorder(30, 60, 30, 60));
        screenMyCosts.add(generateHeading("My Costs"), BorderLayout.PAGE_START);
        screenMyCosts.add(costsList, BorderLayout.LINE_START);
    }

    void buildAddCostScreen() {

        // building name input
        JLabel nameLabel = generateLabel("Name", 60, 30);
        JTextField nameField = generateTextField("", 150, 30);
        JPanel nameInput = new JPanel();
        nameInput.setBackground(Color.WHITE);
        nameInput.add(nameLabel);
        nameInput.add(nameField);

        // building currencies input
        Currency[] currencies = {
                new Currency(null, "USD", 3.24),
                new Currency(null, "GBP", 3.9093),
                new Currency(null, "JPY", 2.3986),
                new Currency(null, "EUR", 3.2987),
        };

        Vector<String> currenciesStrings = new Vector<String>();
        for (var currency : currencies) {
            currenciesStrings.add(currency.getName());
        }

        JComboBox currenciesBox = new JComboBox(currenciesStrings);
        currenciesBox.setSelectedIndex(0);
        currenciesBox.setPreferredSize(new Dimension(70, 30));
        currenciesBox.setBackground(Color.WHITE);
        currenciesBox.setBorder(BorderFactory.createLineBorder(Color.decode("#C5C5C5")));

        // building sum input
        JLabel sumLabel = generateLabel("Sum", 60, 30);
        JTextField sumField = generateTextField("0", 75, 30);
        JPanel sumInput = new JPanel();
        sumInput.setLayout(new FlowLayout(FlowLayout.LEADING));
        sumInput.setBackground(Color.WHITE);
        sumInput.add(sumLabel);
        sumInput.add(sumField);
        sumInput.add(currenciesBox);

        // building categories input
        Category[] categories = {
                new Category(null, "Groceries"),
                new Category(null, "Transport"),
                new Category(null, "Leisure"),
                new Category(null, "Purchases"),
                new Category(null, "Health"),
                new Category(null, "Gifts")
        };

        Vector<String> categoriesStrings = new Vector<String>();
        for (var category : categories) {
            categoriesStrings.add(category.getName());
        }

        JComboBox categoriesBox = new JComboBox(categoriesStrings);
        categoriesBox.setSelectedIndex(0);
        categoriesBox.setPreferredSize(new Dimension(150, 30));
        categoriesBox.setBackground(Color.WHITE);
        categoriesBox.setBorder(BorderFactory.createLineBorder(Color.decode("#C5C5C5")));

        JLabel categoryLabel = generateLabel("Category", 60, 30);
        JPanel categoryInput = new JPanel();
        categoryInput.setBackground(Color.WHITE);
        categoryInput.add(categoryLabel);
        categoryInput.add(categoriesBox);

        // building description input
        JLabel descriptionLabel = generateLabel("Description", 70, 30);

        JTextArea descriptionArea = new JTextArea(2, 5);
        descriptionArea.setPreferredSize(new Dimension(220, 60));
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.decode("#C5C5C5")));
        descriptionArea.setLineWrap(true);

        // building the Add Cost form
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.decode("#F5F5F5"));

        JPanel addCostForm = new JPanel(new SpringLayout());
        addCostForm.setPreferredSize(new Dimension(580, 480));
        addCostForm.setBackground(Color.WHITE);
        addCostForm.add(nameInput, generateSpringConstraint(0, 0));
        addCostForm.add(sumInput, generateSpringConstraint(0, 50));
        addCostForm.add(categoryInput, generateSpringConstraint(0, 100));
        addCostForm.add(descriptionLabel, generateSpringConstraint(0, 150));
        addCostForm.add(descriptionArea, generateSpringConstraint(0, 180));
        addCostForm.add(saveButton, generateSpringConstraint(0, 270));

        // building the Add Cost screen
        screenAddCost.setBackground(Color.WHITE);
        screenAddCost.setBorder(new EmptyBorder(30, 60, 30, 60));
        screenAddCost.add(generateHeading("Add Cost"), BorderLayout.PAGE_START);
        screenAddCost.add(addCostForm, BorderLayout.CENTER);

        // handling events
        saveButton.addActionListener(e -> {
            cards.show(screen, "MyCosts");
            topPanels.show(panelTop, "TopLogout");
            rightPanels.show(panelRight, "RightAuthorized");
        });
    }

    void buildAddCategoryScreen() {

        // building name input
        JLabel nameLabel = generateLabel("Name", 70, 30);
        JTextField nameField = generateTextField("", 130, 30);
        JPanel nameInput = new JPanel();
        nameInput.setBackground(Color.WHITE);
        nameInput.add(nameLabel);
        nameInput.add(nameField);

        // building categories list display
        JTextArea categoriesList = new JTextArea();
        categoriesList.setLayout(new FlowLayout(FlowLayout.LEADING));
        categoriesList.setBackground(Color.decode("#F3F1F1"));
        categoriesList.setBorder(new EmptyBorder(15, 15, 15, 15));
        categoriesList.setPreferredSize(new Dimension(800, 200));
        categoriesList.setLineWrap(true);
        categoriesList.setEditable(false);

        Category[] categories = {
                new Category(null, "Groceries"),
                new Category(null, "Transport"),
                new Category(null, "Leisure"),
                new Category(null, "Purchases"),
                new Category(null, "Health"),
                new Category(null, "Gifts")
        };

        for (var category : categories) {
            JLabel categoryName = new JLabel(category.getName());
            categoryName.setPreferredSize(new Dimension(70, 30));
            categoriesList.add(categoryName);
        }

        // building the Add Category form
        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.decode("#F5F5F5"));

        JPanel addCategoryForm = new JPanel();
        addCategoryForm.setLayout(new SpringLayout());
        addCategoryForm.setPreferredSize(new Dimension(580, 480));
        addCategoryForm.setBackground(Color.WHITE);
        addCategoryForm.add(nameInput, generateSpringConstraint(0, 0));
        addCategoryForm.add(categoriesList, generateSpringConstraint(0, 80));
        addCategoryForm.add(addButton, generateSpringConstraint(0, 300));

        // building the Add Category screen
        screenAddCategory.setBackground(Color.WHITE);
        screenAddCategory.setBorder(new EmptyBorder(30, 60, 30, 60));
        screenAddCategory.add(generateHeading("Add Category"), BorderLayout.PAGE_START);
        screenAddCategory.add(addCategoryForm, BorderLayout.CENTER);

        // handling events
        addButton.addActionListener(e -> {
            cards.show(screen, "AddCategory");
            topPanels.show(panelTop, "TopLogout");
            rightPanels.show(panelRight, "RightAuthorized");
        });
    }

    void buildLoginScreen() {

        // building email input
        JLabel emailLabel = generateLabel("Email", 70, 30);
        JTextField emailField = generateTextField("", 250, 30);
        JPanel emailInput = new JPanel();
        emailInput.setBackground(Color.WHITE);
        emailInput.add(emailLabel);
        emailInput.add(emailField);

        // building password input
        JLabel passwordLabel = generateLabel("Password", 70, 30);
        JPasswordField passwordField = generatePasswordField("", 250, 30);
        JPanel passwordInput = new JPanel();
        passwordInput.setBackground(Color.WHITE);
        passwordInput.add(passwordLabel);
        passwordInput.add(passwordField);

        // building the login form
        JButton submitButton = new JButton("Login");
        submitButton.setBackground(Color.decode("#F5F5F5"));

        JPanel loginForm = new JPanel();
        loginForm.setLayout(new SpringLayout());
        loginForm.setPreferredSize(new Dimension(580, 480));
        loginForm.setBackground(Color.WHITE);
        loginForm.add(emailInput, generateSpringConstraint(0, 0));
        loginForm.add(passwordInput, generateSpringConstraint(0, 50));
        loginForm.add(submitButton, generateSpringConstraint(0, 120));

        // building the login screen
        screenLogin.setBackground(Color.WHITE);
        screenLogin.setBorder(new EmptyBorder(30, 60, 30, 60));
        screenLogin.add(generateHeading("Login"), BorderLayout.PAGE_START);
        screenLogin.add(loginForm, BorderLayout.CENTER);

        // handling events
        submitButton.addActionListener(e -> {
            cards.show(screen, "MyCosts");
            topPanels.show(panelTop, "TopLogout");
            rightPanels.show(panelRight, "RightAuthorized");
        });
    }

    void buildLogupScreen() {

        // building username input
        JLabel usernameLabel = generateLabel("Username", 70, 30);
        JTextField usernameField = generateTextField("", 250, 30);
        JPanel usernameInput = new JPanel();
        usernameInput.setBackground(Color.WHITE);
        usernameInput.add(usernameLabel);
        usernameInput.add(usernameField);

        // building email input
        JLabel emailLabel = generateLabel("Email", 70, 30);
        JTextField emailField = generateTextField("", 250, 30);
        JPanel emailInput = new JPanel();
        emailInput.setBackground(Color.WHITE);
        emailInput.add(emailLabel);
        emailInput.add(emailField);

        // building password input
        JLabel passwordLabel = generateLabel("Password", 70, 30);
        JPasswordField passwordField = generatePasswordField("", 250, 30);
        JPanel passwordInput = new JPanel();
        passwordInput.setBackground(Color.WHITE);
        passwordInput.add(passwordLabel);
        passwordInput.add(passwordField);

        // building the logup form
        JButton submitButton = new JButton("Logup");
        submitButton.setBackground(Color.decode("#F5F5F5"));

        JPanel logupForm = new JPanel();
        logupForm.setLayout(new SpringLayout());
        logupForm.setPreferredSize(new Dimension(580, 480));
        logupForm.setBackground(Color.WHITE);
        logupForm.add(usernameInput, generateSpringConstraint(0, 0));
        logupForm.add(emailInput, generateSpringConstraint(0, 50));
        logupForm.add(passwordInput, generateSpringConstraint(0, 100));
        logupForm.add(submitButton, generateSpringConstraint(0, 170));

        // building the login screen
        screenLogup.setBackground(Color.WHITE);
        screenLogup.setBorder(new EmptyBorder(30, 60, 30, 60));
        screenLogup.add(generateHeading("Login"), BorderLayout.PAGE_START);
        screenLogup.add(logupForm, BorderLayout.CENTER);

        // handling events
        submitButton.addActionListener(e -> {
            cards.show(screen, "MyCosts");
            topPanels.show(panelTop, "TopLogout");
            rightPanels.show(panelRight, "RightAuthorized");
        });
    }

    SpringLayout.Constraints generateSpringConstraint(int xValue, int yValue){

        Spring x = Spring.constant(xValue);
        Spring y = Spring.constant(yValue);
        SpringLayout.Constraints constraint = new SpringLayout.Constraints();
        constraint.setX(x);
        constraint.setY(y);

        return constraint;
    }

    JLabel generateHeading(String text) {

        JLabel heading = new JLabel(text);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBorder(new EmptyBorder(0, 0, 70, 0));

        return heading;
    }

    JLabel generateLabel(String text, int width, int height) {

        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(width, height));

        return label;
    }

    JTextField generateTextField(String text, int width, int height) {

        JTextField textField = new JTextField(text);
        textField.setPreferredSize(new Dimension(width, height));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#C5C5C5")));

        return textField;
    }

    JPasswordField generatePasswordField(String text, int width, int height) {

        JPasswordField passwordField = new JPasswordField(text);
        passwordField.setPreferredSize(new Dimension(width, height));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#C5C5C5")));

        return passwordField;
    }

    @Override
    public void setViewModel(IViewModel ob) {
        vm = ob;
    }

    @Override
    public void start() {

        // setting the layout managers
        frame.setLayout(new BorderLayout());
        screen.setLayout(cards);
        panelTop.setLayout(topPanels);
        panelRight.setLayout(rightPanels);
        panelTopLogin.setLayout(new BorderLayout());
        panelTopLogup.setLayout(new BorderLayout());
        panelTopLogout.setLayout(new BorderLayout());
        screenMyCosts.setLayout(new BorderLayout());
        screenAddCost.setLayout(new BorderLayout());
        screenAddCategory.setLayout(new BorderLayout());
        screenLogin.setLayout(new BorderLayout());
        screenLogup.setLayout(new BorderLayout());

        // setting cards
        screen.add(screenMyCosts, "MyCosts");
        screen.add(screenAddCost, "AddCost");
        screen.add(screenAddCategory, "AddCategory");
        screen.add(screenLogin, "Login");
        screen.add(screenLogup, "Logup");
        cards.show(screen, "Login");

        panelTop.add(panelTopLogin, "TopLogin");
        panelTop.add(panelTopLogup, "TopLogup");
        panelTop.add(panelTopLogout, "TopLogout");
        topPanels.show(panelTop, "TopLogup");

        panelRight.add(panelRightAuthorized, "RightAuthorized");
        panelRight.add(panelRightUnauthorized, "RightUnauthorized");
        rightPanels.show(panelRight, "RightUnauthorized");

        // building the layout panels
        buildTopPanel();
        buildRightPanel();

        // building the screens
        screen.setPreferredSize(new Dimension(640, 640));

        buildMyCostsScreen();
        buildAddCostScreen();
        buildAddCategoryScreen();
        buildLoginScreen();
        buildLogupScreen();

        // building the layout
        frame.add(panelTop, BorderLayout.PAGE_START);
        frame.add(screen, BorderLayout.CENTER);
        frame.add(panelRight, BorderLayout.LINE_END);

        // showing the frame
        frame.setSize(800,600);
        frame.setVisible(true);

        // handling frame events
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
