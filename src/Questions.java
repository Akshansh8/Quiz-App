
public class Questions {
	private int id; // question id
	private String question;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private String answer;
	
	public Questions(int id, String question, String opt1, String opt2, String opt3, String opt4, String answer)
	{
		if(id <=0 || question.isEmpty() || opt1.isEmpty() || opt2.isEmpty() || opt3.isEmpty() || opt4.isEmpty()) {
			throw new IllegalArgumentException("Invalid questions or options provided");
		}
		this.id = id;
		this.question = question;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
		this.answer = answer;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId (int id ) {
		if(id <= 0) {
			throw new IllegalArgumentException("ID must be greater than 0");
		}
		this.id = id;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public void setQuestion(String question) {
		if (question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty.");
        }
		this.question = question;
	}
	 public String getOpt1() {
	        return opt1;
	    }

	    public void setOpt1(String opt1) {
	    	if(opt1.isEmpty()) {
	    		throw new IllegalArgumentException("Empty option provided");
	    	}
	        this.opt1 = opt1;
	    }

	    public String getOpt2() {
	        return opt2;
	    }

	    public void setOpt2(String opt2) {
	    	if(opt2.isEmpty()) {
	    		throw new IllegalArgumentException("Empty option provided");
	    	}
	        this.opt2 = opt2;
	    }

	    public String getOpt3() {
	        return opt3;
	    }

	    public void setOpt3(String opt3) {
	    	if(opt3.isEmpty()) {
	    		throw new IllegalArgumentException("Empty option provided");
	    	}
	        this.opt3 = opt3;
	    }

	    public String getOpt4() {
	        return opt4;
	    }

	    public void setOpt4(String opt4) {
	    	if(opt4.isEmpty()) {
	    		throw new IllegalArgumentException("Empty option provided");
	    	}
	        this.opt4 = opt4;
	    }

	    public String getAnswer() {
	        return answer;
	    }

	    public void setAnswer(String answer) {
	    	if(answer.isEmpty()) {
	    		throw new IllegalArgumentException("Empty answer provided");
	    	}
	        this.answer = answer;
	    }
	    
	    
	    public String toString() {
	    	return "Question{" +
	                "id=" + id +
	                ", question='" + question + '\''+
	                ", opt1='" + opt1 + '\'' +
	                ", opt1='" + opt2 + '\'' +
	                ", opt1='" + opt3 + '\'' +
	                ", opt1='" + opt4 + '\'' +
	                ", answer='" + answer +'\'' +
	                '}';
	                
	    }
	

}
