package il.ac.shenkar.costmanager.client.view;

import il.ac.shenkar.costmanager.client.viewmodel.IViewModel;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Cost;
import il.ac.shenkar.costmanager.entities.Currency;
import il.ac.shenkar.costmanager.entities.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class View implements IView {

    private IViewModel vm;
    private JFrame frame;
    private CardLayout cards, topPanels, rightPanels;
    private JPanel screen,screenMyCosts, screenAddCost, screenAddCategory, screenLogin, screenLogup;
    private JPanel panelTopLogin, panelTopLogup, panelTopLogout, panelTop, panelRightAuthorized, panelRightUnauthorized, panelRight;
    private JButton buttonMyCosts, buttonAddCost, buttonAddCategory, buttonLogin, buttonLogup, buttonLogout;
    JComboBox currenciesBox;
    JComboBox categoriesBox;
    JTextArea categoriesList;
    JPanel costsList;
    JLabel emptyCostsMessage;

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

            removeItemsFromModel();
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
        costsList.setPreferredSize(new Dimension(800, 480));
        costsList.setBackground(Color.WHITE);

        // building the MY Costs screen
        screenMyCosts.setBackground(Color.WHITE);
        screenMyCosts.setBorder(new EmptyBorder(30, 60, 30, 60));
        screenMyCosts.add(generateHeading("My Costs"), BorderLayout.PAGE_START);
        screenMyCosts.add(costsList, BorderLayout.LINE_START);
    }

    void buildAddCostScreen() {

        // building categories input
        categoriesBox.setPreferredSize(new Dimension(150, 30));
        categoriesBox.setBackground(Color.WHITE);
        categoriesBox.setBorder(BorderFactory.createLineBorder(Color.decode("#C5C5C5")));

        JLabel categoryLabel = generateLabel("Category", 60, 30);
        JPanel categoryInput = new JPanel();
        categoryInput.setBackground(Color.WHITE);
        categoryInput.add(categoryLabel);
        categoryInput.add(categoriesBox);

        // building currencies input
        currenciesBox.setPreferredSize(new Dimension(70, 30));
        currenciesBox.setBackground(Color.WHITE);
        currenciesBox.setBorder(BorderFactory.createLineBorder(Color.decode("#C5C5C5")));

        // building sum input
        JLabel sumLabel = generateLabel("Sum", 60, 30);
        JTextField sumField = generateTextField("", 75, 30);
        JPanel sumInput = new JPanel();
        sumInput.setLayout(new FlowLayout(FlowLayout.LEADING));
        sumInput.setBackground(Color.WHITE);
        sumInput.add(sumLabel);
        sumInput.add(sumField);
        sumInput.add(currenciesBox);

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
        addCostForm.add(categoryInput, generateSpringConstraint(0, 0));
        addCostForm.add(sumInput, generateSpringConstraint(0, 50));
        addCostForm.add(descriptionLabel, generateSpringConstraint(0, 100));
        addCostForm.add(descriptionArea, generateSpringConstraint(0, 130));
        addCostForm.add(saveButton, generateSpringConstraint(0, 220));

        // building the Add Cost screen
        screenAddCost.setBackground(Color.WHITE);
        screenAddCost.setBorder(new EmptyBorder(30, 60, 30, 60));
        screenAddCost.add(generateHeading("Add Cost"), BorderLayout.PAGE_START);
        screenAddCost.add(addCostForm, BorderLayout.CENTER);

        // handling events
        saveButton.addActionListener(e -> {

            // do nothing if the sum is empty
            String sum = sumField.getText();
            if (sum.isEmpty() || sum.isBlank()) {
                return;
            }

            // add the new cost
            Category category = (Category)categoriesBox.getSelectedItem();
            Currency currency = (Currency)currenciesBox.getSelectedItem();
            String description = descriptionArea.getText().isEmpty() || descriptionArea.getText().isBlank() ? "" : descriptionArea.getText();
            Cost cost = new Cost(
                    null,
                    "91966493-d06c-4593-bdb2-0fb1a084b6f8",
                    category.getCategoryId(),
                    Double.parseDouble(sum),
                    currency.getCurrencyId(),
                    description,
                    null);

            vm.addCost(cost);

            // clear the fields
            sumField.setText("");
            descriptionArea.setText("");

            // move to the My Costs screen
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
        categoriesList.setLayout(new FlowLayout(FlowLayout.LEADING));
        categoriesList.setBackground(Color.decode("#F3F1F1"));
        categoriesList.setBorder(new EmptyBorder(15, 15, 15, 15));
        categoriesList.setPreferredSize(new Dimension(800, 200));
        categoriesList.setLineWrap(true);
        categoriesList.setEditable(false);

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

            // do nothing if the name field is empty
            String newCategoryName = nameField.getText();
            if (newCategoryName.isBlank() || newCategoryName.isEmpty()) {
                return;
            }

            // add the new category
            Category category = new Category(null, "91966493-d06c-4593-bdb2-0fb1a084b6f8", newCategoryName);
            vm.addCategory(category);

            // clear the fields
            nameField.setText("");
            categoriesBox.removeAllItems();
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

            addItemsFromModel();
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

            addItemsFromModel();
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

    void addItemsFromModel() {

        vm.getCurrencies();
        vm.getCategories();
        vm.getCosts();
    }

    void removeItemsFromModel() {

        currenciesBox.removeAllItems();
        categoriesBox.removeAllItems();
        categoriesList = new JTextArea();
        costsList.removeAll();
    }

    void generateCostsList(List<Cost> costs) {

        costsList.removeAll();

        if (costs.size() == 0) {
            JLabel emptyMessage = generateLabel("Empty List", 70, 30);
            costsList.add(emptyMessage);
        }

        for (var cost : costs) {
            JTextArea costPanel = new JTextArea();
            costPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            costPanel.setBackground(Color.decode("#F3F1F1"));
            costPanel.setPreferredSize(new Dimension(800, 60));
            costPanel.setBorder(new EmptyBorder(20, 10, 10, 10));

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cost.getCategoryId());
            stringBuffer.append("\t\t");
            stringBuffer.append(cost.getSum());
            stringBuffer.append(" ");
            stringBuffer.append(cost.getCurrencyId());
            stringBuffer.append("\t\t");
            stringBuffer.append(cost.getDescription());

            costPanel.setText(stringBuffer.toString());
            costsList.add(costPanel);
        }

        costsList.revalidate();
        costsList.repaint();
    }

    @Override
    public void setViewModel(IViewModel ob) {
        vm = ob;
    }

    @Override
    public void setCategories(List<Category> categories) {

        StringBuffer stringBuffer = new StringBuffer();
        for (var category : categories) {
            stringBuffer.append(category.getName());
            stringBuffer.append("\t");

            categoriesBox.addItem(category);
        }

        categoriesList.setText(stringBuffer.toString());

        categoriesBox.setSelectedIndex(0);
    }

    @Override
    public void setCosts(List<Cost> costs) {

        if(SwingUtilities.isEventDispatchThread()) {
            generateCostsList(costs);
        } else {
            SwingUtilities.invokeLater(() -> {
                generateCostsList(costs);
            });
        }
    }

    @Override
    public void setCurrencies(List<Currency> currenciesList) {

        for (var currency : currenciesList) {
            currenciesBox.addItem(currency);
        }

        currenciesBox.setSelectedIndex(0);
    }

    @Override
    public void setUsers(List<User> users) {

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

        // setting components
        currenciesBox = new JComboBox();
        categoriesBox = new JComboBox();
        categoriesList = new JTextArea();
        costsList = new JPanel();
        emptyCostsMessage = new JLabel("Empty List.");

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
