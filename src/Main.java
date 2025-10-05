import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main extends JFrame {
    private JTextField nameField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField placeField;
    private JTextArea resultArea;
    private JButton calculateButton;
    private JButton clearButton;
    
    // Zodiac signs and their date ranges
    private static final String[] ZODIAC_SIGNS = {
        "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
        "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"
    };
    
    public Main() {
        setTitle("Astrology App - Birth Chart Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Create input panel
        JPanel inputPanel = createInputPanel();
        
        // Create button panel
        JPanel buttonPanel = createButtonPanel();
        
        // Create result panel
        JPanel resultPanel = createResultPanel();
        
        // Add panels to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Birth Information"));
        
        // Name field
        panel.add(new JLabel("Full Name:"));
        nameField = new JTextField(20);
        panel.add(nameField);
        
        // Date field
        panel.add(new JLabel("Birth Date (YYYY-MM-DD):"));
        dateField = new JTextField(20);
        panel.add(dateField);
        
        // Time field
        panel.add(new JLabel("Birth Time (HH:MM):"));
        timeField = new JTextField(20);
        panel.add(timeField);
        
        // Place field
        panel.add(new JLabel("Birth Place:"));
        placeField = new JTextField(20);
        panel.add(placeField);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        calculateButton = new JButton("Calculate Horoscope");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateHoroscope();
            }
        });
        
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        
        panel.add(calculateButton);
        panel.add(clearButton);
        
        return panel;
    }
    
    private JPanel createResultPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Astrological Information"));
        
        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void calculateHoroscope() {
        String name = nameField.getText().trim();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String place = placeField.getText().trim();
        
        if (name.isEmpty() || date.isEmpty() || time.isEmpty() || place.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill in all fields",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Parse the date
            LocalDate birthDate = LocalDate.parse(date);
            String zodiacSign = getZodiacSign(birthDate.getMonthValue(), birthDate.getDayOfMonth());
            String element = getElement(zodiacSign);
            String rulingPlanet = getRulingPlanet(zodiacSign);
            String characteristics = getCharacteristics(zodiacSign);
            
            // Build result string
            StringBuilder result = new StringBuilder();
            result.append("═══════════════════════════════════════════════════════════\n");
            result.append("           ASTROLOGICAL BIRTH CHART ANALYSIS\n");
            result.append("═══════════════════════════════════════════════════════════\n\n");
            result.append("Name: ").append(name).append("\n");
            result.append("Birth Date: ").append(date).append("\n");
            result.append("Birth Time: ").append(time).append("\n");
            result.append("Birth Place: ").append(place).append("\n\n");
            result.append("───────────────────────────────────────────────────────────\n");
            result.append("Sun Sign (Zodiac): ").append(zodiacSign).append("\n");
            result.append("Element: ").append(element).append("\n");
            result.append("Ruling Planet: ").append(rulingPlanet).append("\n\n");
            result.append("───────────────────────────────────────────────────────────\n");
            result.append("CHARACTERISTICS:\n\n");
            result.append(characteristics).append("\n");
            result.append("═══════════════════════════════════════════════════════════\n");
            
            resultArea.setText(result.toString());
            resultArea.setCaretPosition(0);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Invalid date format. Please use YYYY-MM-DD format.",
                "Date Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getZodiacSign(int month, int day) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "Aries";
        if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return "Taurus";
        if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) return "Gemini";
        if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) return "Cancer";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "Leo";
        if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "Virgo";
        if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return "Libra";
        if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) return "Scorpio";
        if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) return "Sagittarius";
        if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) return "Capricorn";
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return "Aquarius";
        return "Pisces";
    }
    
    private String getElement(String zodiacSign) {
        switch (zodiacSign) {
            case "Aries":
            case "Leo":
            case "Sagittarius":
                return "Fire";
            case "Taurus":
            case "Virgo":
            case "Capricorn":
                return "Earth";
            case "Gemini":
            case "Libra":
            case "Aquarius":
                return "Air";
            case "Cancer":
            case "Scorpio":
            case "Pisces":
                return "Water";
            default:
                return "Unknown";
        }
    }
    
    private String getRulingPlanet(String zodiacSign) {
        switch (zodiacSign) {
            case "Aries": return "Mars";
            case "Taurus": return "Venus";
            case "Gemini": return "Mercury";
            case "Cancer": return "Moon";
            case "Leo": return "Sun";
            case "Virgo": return "Mercury";
            case "Libra": return "Venus";
            case "Scorpio": return "Pluto/Mars";
            case "Sagittarius": return "Jupiter";
            case "Capricorn": return "Saturn";
            case "Aquarius": return "Uranus/Saturn";
            case "Pisces": return "Neptune/Jupiter";
            default: return "Unknown";
        }
    }
    
    private String getCharacteristics(String zodiacSign) {
        switch (zodiacSign) {
            case "Aries":
                return "Courageous, determined, confident, enthusiastic, optimistic, honest, passionate. Natural leaders with pioneering spirit.";
            case "Taurus":
                return "Reliable, patient, practical, devoted, responsible, stable. Values security and enjoys material comforts.";
            case "Gemini":
                return "Gentle, affectionate, curious, adaptable, quick learner, witty. Excellent communicators with versatile interests.";
            case "Cancer":
                return "Tenacious, highly imaginative, loyal, emotional, sympathetic, persuasive. Strong attachment to family and home.";
            case "Leo":
                return "Creative, passionate, generous, warm-hearted, cheerful, humorous. Natural performers who love to be in the spotlight.";
            case "Virgo":
                return "Loyal, analytical, kind, hardworking, practical. Attention to detail and strong sense of humanity.";
            case "Libra":
                return "Cooperative, diplomatic, gracious, fair-minded, social. Strong sense of justice and appreciation for beauty.";
            case "Scorpio":
                return "Resourceful, brave, passionate, stubborn, true friend. Intense emotions and powerful presence.";
            case "Sagittarius":
                return "Generous, idealistic, great sense of humor. Love for freedom, travel, and philosophical pursuits.";
            case "Capricorn":
                return "Responsible, disciplined, self-control, good managers. Ambitious with strong commitment to goals.";
            case "Aquarius":
                return "Progressive, original, independent, humanitarian. Innovative thinkers who value intellectual connections.";
            case "Pisces":
                return "Compassionate, artistic, intuitive, gentle, wise, musical. Deeply empathetic with rich inner world.";
            default:
                return "Characteristics unknown.";
        }
    }
    
    private void clearFields() {
        nameField.setText("");
        dateField.setText("");
        timeField.setText("");
        placeField.setText("");
        resultArea.setText("");
    }
    
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main app = new Main();
                app.setVisible(true);
            }
        });
    }
}
