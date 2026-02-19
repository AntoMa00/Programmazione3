//package it.bripobe.DecoratorFiles;
//
//public class MainDecorator {
//    public static void main(String[] args) {
//        // 1. Creiamo un prodotto normale
//        Prodotto telefono = new PrezzoBaseProdotto("T001", "iPhone 15", 1000.00);
//
//        System.out.println("--- Prodotto Normale ---");
//        System.out.println(telefono.getDescrizione() + " costa: " + telefono.getPrezzo() + "€");
//
//        // 2. L'Amministratore decide di applicare uno sconto del 20%
//        // Decoriamo l'oggetto telefono
//        Prodotto telefonoScontato = new ScontoPercentualeDecorator(telefono, 20.0);
//
//        System.out.println("\n--- Prodotto Scontato ---");
//        // Nota: chiamiamo gli stessi metodi, ma il comportamento è cambiato!
//        System.out.println(telefonoScontato.getDescrizione());
//        // Output: iPhone 15 (Sconto 20.0%)
//        System.out.println("Prezzo finale: " + telefonoScontato.getPrezzo() + "€");
//        // Output: Prezzo finale: 800.0€
//    }
//}