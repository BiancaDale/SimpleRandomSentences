/*
This program implements the below rules to generate random sentences. 

  The program generates and outputs one random sentence every three seconds until
  it is halted (for example, by typing Control-C in the terminal window where it is
  running).

Rules that capture the syntax of this verse:
	<sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]
	
	<simple_sentence> ::= <noun_phrase> <verb_phrase>
	
	<noun_phrase> ::= <proper_noun> |
	<determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]
	
	<verb_phrase> ::= <intransitive_verb> |
	<transitive_verb> <noun_phrase> |
	is <adjective> |
	believes that <simple_sentence>
	
	<conjunction> ::= and | or | but | because
	
	<proper_noun> ::= Fred | Jane | Richard Nixon | Miss America
	
	<common_noun> ::= man | woman | fish | elephant | unicorn
	
	<determiner> ::= a | the | every | some
	
	<adjective> ::= big | tiny | pretty | bald
	
	<intransitive_verb> ::= runs | jumps | talks | sleeps
	
	<transitive_verb> ::= loves | hates | sees | knows | looks for | finds        

  
 */

// syntax rules
public class SimpleRandomSentences {

	static final String [] conjunction = {"and", "or", "but",  "because"};
	
	static final String [] proper_noun = {"Fred", "Jane", "Richard Nixon", "Miss America"};

	static final String [] common_noun = {"man", "woman", "fish", "elephant", "unicorn"};

	static final String [] determiner = {"a", "the", "every", "some"};

	static final String [] adjective = {"big", "tiny",  "pretty",  "bald"};

	static final String [] intransitive_verb = {"runs",  "jumps",  "talks",  "sleeps"};

	static final String [] transitive_verb = {"loves", "hates", "sees",  "knows", "looks for", "finds"};

	// generates a random sentence every three seconds by calling the randomSentence method 
	public static void main(String[] args) {
		while (true) {
			randomSentence();
			System.out.println(".\n\n");
			try {
				Thread.sleep(3000);
			}
			catch (InterruptedException e) {
			}
		}
	}

	/* Method representing the rules 
	 * <simple_sentence> ::= <noun_phrase> <verb_phrase> 
	 * <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ] 
	 * */
	  static void randomSentence() {		 
	      			randomNounPhrase();
	              randomVerbPhrase();       
	      if (Math.random() > 0.75) {
	              System.out.print(" " + randomItem(conjunction));
	              randomSentence();
	      }
	   }
	  
	  /* Method representing the rules
	   * <noun_phrase> ::= <proper_noun> |
	   * <determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]
	   * */
	static void randomNounPhrase() {

		if (Math.random() > 0.75) {
			System.out.print(" " + randomItem(proper_noun)); 
		} else {
			System.out.print(" " + randomItem(determiner)); 	 
		}
		if (Math.random() > 0.5) {
			System.out.print(" " + randomItem(adjective)+".");
			System.out.print(" " + randomItem(common_noun));
			System.out.print(" who "); 
			randomVerbPhrase();
		}
	}

	/* Method representing the rules 
	 * <verb_phrase> ::= <intransitive_verb> |
	 * <transitive_verb> <noun_phrase> |
	 * is <adjective> |
	 * believes that <simple_sentence> 
	 * */
	
	static void randomVerbPhrase() {
		if (Math.random() > 0.75) {
			System.out.print(" " + randomItem(intransitive_verb)); 
		} else if (Math.random() > 0.5){
			System.out.print(" " + randomItem(transitive_verb));
			randomNounPhrase();
		}
		else if (Math.random() > 0.3){
			System.out.print(" is " + randomItem(adjective));
			randomNounPhrase();
		}
		else {
			System.out.print(" believes that ");
			randomSentence();
		}		
	}
	
	// picks a random item from an array of strings
	static String randomItem(String[] listOfStrings) {
		return listOfStrings[(int)(Math.random()*listOfStrings.length)];
	}
}
