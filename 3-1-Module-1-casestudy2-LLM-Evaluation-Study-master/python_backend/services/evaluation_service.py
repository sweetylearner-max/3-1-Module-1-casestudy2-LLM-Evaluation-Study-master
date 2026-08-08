from metrics.accuracy import calculate_accuracy
from metrics.coherence import calculate_coherence
from metrics.perplexity import calculate_perplexity

def evaluate_text(prompt, response):

    return {
        "status": "success",
        "accuracy": calculate_accuracy(prompt, response),
        "coherence": calculate_coherence(response),
        "perplexity": calculate_perplexity(response)
    }