package dataScience;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

/**
 * A ternary-search-tree implementation of a text-autocompletion
 * trie, a simplified version of some autocomplete software.
 * @author Scott Nelson
 * collaborated with Will Judy
 */
public class TernaryTreeTextFiller implements TextFiller {

    // Fields
    // -----------------------------------------------------------
    private TTNode root;
    private int size;
    
    // Constructor
    // -----------------------------------------------------------
    public TernaryTreeTextFiller () {
        this.root = null;
        this.size = 0;
    }
    
    
    // Methods
    // -----------------------------------------------------------
    
    public int size () {
        return size;
    }

    public boolean empty () {
        return (size == 0);
    }
    /*****		this is probably 
     ******* 	not a haiku because I
     ***** 		am not good at this */
    
    
    /*
     * @param takes a String toAdd 
 	 * puts the string in the trie
     */
    public void add (String toAdd) {
        this.add(toAdd, this.root);
    }
    
    /*
     * boolean function 
     * returns True if string exists in the given trie
     * returns false otherwise
     */
    public boolean contains (String query) {
    	if (root == null) {
    		return false;
    	}
        return this.contains(query, this.root);
    }
    
    /*
     * given the parameter String query
     * textFill returns a string within the tree that could complete/autofill text
     */
    public String textFill (String query) {
    	if (this.contains(query)) {
    		//the query is valid, if contained.
    		return query;
    	}
    	
    	if (endOfPrefix(query, root) == null) {
    		//returns null if prefix DNE in the trie (i.e. meets null where prefixletter should be)
    		return null;
    	}
    	
    	TTNode lastNodeOfPrefix = endOfPrefix(query, root);
    	String suffix = suffixSupport("", lastNodeOfPrefix);
    	System.out.println("query( " + query + ")" + " + suffix (" + suffix + ")");
    	return query + suffix;
    }
    
    
    /*					GET SORTED LIST
     * 
     * @returns an ArrayList of Strings 
     * 		consisting of the alphabetically sorted search terms within this TextFiller.
     */
    public List<String> getSortedList () {
    	
    	ArrayList<String> dict = new ArrayList<String>();
    	String word = "";
    	
        getSortedList(dict, this.root, word);
        
        return dict;
    }
    // Private Helper Methods
    // -----------------------------------------------------------
    
    
    //_________________________________________________________________________
    /*						ADD
     * ------------------------------------------------------------------------
     * @param String, TTNode
     * 	finds the appropriate node to place the string toAdd into the trie
     */
    private void add (String toAdd, TTNode n) { //
    	//empty string? ur done.
    	if (toAdd.length() == 0) {
    		return;
    	}
    	if (n == null) {
    		root = new TTNode(false);
    		n = root;
    	}
    	//if the currentNode n is empty, we can add to it. 
    	if (n.letter == 0) {
    		n.letter = toAdd.charAt(0);
    		//last letter of string-->no more chars to add
    		if (toAdd.length() == 1) {
    			size++; //new word added!
    			n.wordEnd = true;
    			return;
    		} 
    		//add the remainder of string
    		for ( int i = 1; i < toAdd.length(); i++ ) {
    			if (i == toAdd.length() - 1) {
    				size++;
    				n.mid = new TTNode(toAdd.charAt(i), true);
    				return;
    			} else {
    				n.mid = new TTNode(toAdd.charAt(i), false);
    				n = n.mid;
    			}
    		}
    	
    	} else { //if the currentNode n HAS A LETTER, -->compare to recurse on a leaf
    		
    		//RECURSIVE CASE(s)
    		if (compareChars(toAdd.charAt(0), n.letter) == 0 ) {
    			//compare with the next letter
    			toAdd = toAdd.substring(1);
    			if (n.mid == null) {
    				//add a new TTnode with *empty children*
    				n.mid = new TTNode(false);
    			}
    			this.add(toAdd, n.mid);
    			
    			
    		} else if ( compareChars(toAdd.charAt(0), n.letter) < 0 ) {
    			if (n.left == null) {
    				n.left = new TTNode(false);
    			}
    			this.add(toAdd, n.left);
    			
    		} else { 
    			if (n.right == null) {
    				n.right = new TTNode(false);
    			}
    			this.add(toAdd, n.right);
    		}
    	}
    }
    
    //_________________________________________________________________________
    /*						CONTAINS
     * ------------------------------------------------------------------------
     * @param String, TTNode
     * 	traverses the trie with param
     * 	if the end of the string is found recursively, String is contained==true
     * 	if no wordEnd is found, or null is bumped into==false
     * @return boolean
     */
    private boolean contains(String query, TTNode n) {
    	char currentLetter = query.charAt(0);
    	
    	//is the currentLetter before, after or equal to root of subtrie?
    	int comparison = compareChars(currentLetter, n.letter); 
    	if (comparison < 0) {
    		//left
    		if (n.left == null) {
    			return false;
    		} else {
    			return contains(query, n.left);
    		}
    	} else if (comparison == 0) {
    		//mid
    		if (query.length() == 1 && n.wordEnd) {
    			return true;
    		} else if (query.length() == 1 && !n.wordEnd) {
    			return false;
    		}
    		if (n.mid == null) {
    			return false;
    		} else {
    			return contains(query.substring(1), n.mid);
    		}
    	} else {
    		//right, right?
    		if (n.right == null) {
    			return false;
    		} else {
    			return contains(query, n.right);
    		}
    	}
    }
    //_________________________________________________________________________
    /*				END OF PREFIX
     * ------------------------------------------------------------------------
     * @param String, TTNode
     * Traverse tree starting with root node, until the prefix is found 
     * once this node is found
     * @return TTNode
     */
    private TTNode endOfPrefix(String prefixWord, TTNode n) { 
    	if (prefixWord.length() == 0) {
    		//if word is exhausted, return @node
    		return n;
    	}
    	if (compareChars(prefixWord.charAt(0), n.letter)==0) {
    		if (n.mid == null) {
    			//none left to check, return @node
    			return n;
    		}
    		if (n.wordEnd && prefixWord.length() == 1) {
    			//found word
    			return n;
    		}
    		//recurse mid-line
    		return endOfPrefix(prefixWord.substring(1), n.mid);
    		
    		
    		//go LEFT towards query
    	} else if ( compareChars(prefixWord.charAt(0), n.letter) < 0 ) {
    		if (n.left != null) {
    			return endOfPrefix(prefixWord, n.left);
    		} 
    		return null;
    	} else { 
    		//go RIGHT towards query 
    		if (n.right != null) {
    			return endOfPrefix(prefixWord, n.right);
    		}
    		return null;
    	}
    }
    //_________________________________________________________________________
    /*				SUFFIX SUPPORT
     * ------------------------------------------------------------------------
     * @param, String, TTNode
     * String given is the available prefix to work with + corresponding TTNode
     * it will find the first available suffix (L, M, R)
     * @return Suffix
     */
    private String suffixSupport(String suffix, TTNode lpNode) {
    	suffix += lpNode.letter;
    	if (lpNode.wordEnd) {
    		return suffix;
    	}
    	if (lpNode.left != null) {
    		return suffixSupport(suffix, lpNode.left);
    	}
    	if (lpNode.mid != null) {
    		return suffixSupport(suffix, lpNode.mid);
    	}
    	if (lpNode.right != null) {
    		return suffixSupport(suffix, lpNode.right);
    	}
    	return suffix;
    }
    
    private void getSortedList(ArrayList<String> dict, TTNode noder, String word) {
    	//END CASE
    	if (noder == null) {
    		return;
    	}
    	getSortedList(dict, noder.left, word);
    	
    	String addition = word + noder.letter;
    	if (noder.wordEnd) {
    		System.out.println("+" + addition);
    		dict.add(addition);
    	}
    	
    	getSortedList(dict, noder.mid, addition);
    	getSortedList(dict, noder.right, word);
    }
    
    /**
     * Normalizes a term to either add or search for in the tree,
     * since we do not want to allow the addition of either null or
     * empty strings within, including empty spaces at the beginning
     * or end of the string (spaces in the middle are fine, as they
     * allow our tree to also store multi-word phrases).
     * @param s The string to sanitize
     * @return The sanitized version of s
     */
    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }
   
    /**
     * Given two characters, c1 and c2, determines whether c1 is
     * alphabetically less than, greater than, or equal to c2
     * @param c1 The first character
     * @param c2 The second character
     * @return
     *   - some int less than 0 if c1 is alphabetically less than c2 
     *   - 0 if c1 is equal to c2
     *   - some int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }
    
    // TTNode Internal Storage
    // -----------------------------------------------------------
    
    /**
     * Internal storage of textfiller search terms
     * as represented using a Ternary Tree (TT) with TTNodes
     * [!] Note: these are currently implemented for the base-assignment;
     *     those endeavoring the extra-credit may need to make changes
     *     below (primarily to the fields and constructor)
     */
    private class TTNode {
        
        boolean wordEnd;
        char letter;
        TTNode left, mid, right;
        
        /**
         * Constructs a new TTNode containing the given character
         * and whether or not it represents a word-end, which can
         * then be added to the existing tree.
         * @param letter Letter to store at this node
         * @param wordEnd Whether or not this is a word-ending letter
         */
        TTNode (char letter, boolean wordEnd) {
            this.letter  = letter;
            this.wordEnd = wordEnd;
            //below because letter value was unspecified
        }
        TTNode(boolean wordEnd) {
        	this.wordEnd = wordEnd;
        	this.letter = 0;
        }
        
    }
    public static void main(String args[]) {
    	TernaryTreeTextFiller tf = new TernaryTreeTextFiller();
    	System.out.println("start adding");
        tf.add("is");
        tf.add("it");
        tf.add("as");
        tf.add("itinerary");
        tf.add("ass");
        tf.add("at");
        tf.add("zoo");
        tf.add("bat");
        tf.add("bother");
        tf.getSortedList();
    }
}
