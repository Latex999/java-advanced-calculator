package com.calculator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to store and manage the calculation history.
 */
public class CalculationHistory {
    
    private static final int MAX_HISTORY_SIZE = 100;
    private final List<String> entries;
    
    /**
     * Constructor initializes an empty history.
     */
    public CalculationHistory() {
        entries = new ArrayList<>();
    }
    
    /**
     * Adds a new entry to the calculation history.
     *
     * @param entry The calculation entry to add
     */
    public void addEntry(String entry) {
        // Add the new entry at the beginning of the list
        entries.add(0, entry);
        
        // Trim the history if it exceeds the maximum size
        if (entries.size() > MAX_HISTORY_SIZE) {
            entries.remove(entries.size() - 1);
        }
    }
    
    /**
     * Returns an unmodifiable view of the history entries.
     *
     * @return The list of history entries
     */
    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }
    
    /**
     * Clears all entries from the history.
     */
    public void clear() {
        entries.clear();
    }
    
    /**
     * Returns the number of entries in the history.
     *
     * @return The size of the history
     */
    public int size() {
        return entries.size();
    }
    
    /**
     * Checks if the history is empty.
     *
     * @return true if the history is empty, false otherwise
     */
    public boolean isEmpty() {
        return entries.isEmpty();
    }
    
    /**
     * Gets a specific entry from the history.
     *
     * @param index The index of the entry to retrieve
     * @return The entry at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String getEntry(int index) {
        if (index < 0 || index >= entries.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + entries.size());
        }
        return entries.get(index);
    }
}