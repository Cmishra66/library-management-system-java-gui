import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }
}

public class LibraryManagementSystemGUI extends JFrame {
    private Library library;
    private JTextArea textArea;
    private JTextField idField, titleField, authorField;

    public LibraryManagementSystemGUI() {
        library = new Library();
        initialize();
    }

    private void initialize() {
        setTitle("Library Management System");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        inputPanel.add(addBookButton);

        JButton displayBooksButton = new JButton("Display Books");
        displayBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayBooks();
            }
        });
        inputPanel.add(displayBooksButton);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            Book book = new Book(id, title, author);
            library.addBook(book);
            JOptionPane.showMessageDialog(this, "Book added successfully.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayBooks() {
        StringBuilder sb = new StringBuilder();
        List<Book> books = library.getBooks();
        if (books.isEmpty()) {
            sb.append("No books available in the library.");
        } else {
            sb.append("Books available in the library:\n");
            for (Book book : books) {
                sb.append(book).append("\n");
            }
        }
        textArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryManagementSystemGUI().setVisible(true);
            }
        });
    }
}