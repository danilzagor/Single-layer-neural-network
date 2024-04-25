# Single-layer-neural-network
Single-layer neural network for language identification of a given text
I've implemented a single-layer neural network for language identification, a project aimed at automatically recognizing the language of a given text.

The neural network architecture consists of a single layer of neurons, where each neuron corresponds to a unique language present in the dataset. The network learns to classify texts into these languages based on the patterns in the training data.

For input representation, I encode each text as a vector representing the proportion of each ASCII character in the text. This vector serves as the input to the neural network.

During training, the network adjusts its weights using the delta rule, a common learning algorithm for neural networks. After each learning step, the weight vectors can be normalized to enhance the classification performance.

To create the training dataset, I've manually curated text samples for each language of interest, ensuring that each language can be represented by ASCII characters. These samples are organized into separate folders, with each folder corresponding to a specific language.

For testing, I've developed an interface that allows users to input short text passages. The system then classifies these passages into the appropriate language using the trained neural network model.

Overall, my language identification system demonstrates the effectiveness of neural networks in automating the language recognition process, with potential applications in various fields such as natural language processing and multilingual content analysis.
