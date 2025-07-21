from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json()
    
    hydration = data.get("hydrationLevel", 1.0)
    frequency = data.get("bowelFrequency", 1)

    # Simple rule-based logic (simulate ML)
    if hydration < 0.4 or frequency < 1:
        return jsonify(result="ALERT")
    else:
        return jsonify(result="NORMAL")
    
@app.route('/')
def health():
    return "OK", 200

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=6000)
