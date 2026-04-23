from flask import Flask, request, jsonify
from pi_producer import send_sensor_data
from datetime import datetime

app = Flask(__name__)

@app.route("/data", methods=["POST"])
def receive_data():
    data = request.get_json()
    print("Received JSON:", data)

    if not data:
        return jsonify({"status": "error", "message": "No JSON provided"}), 400

    player = data.get("player")
    player_id = data.get("player_id")
    level = data.get("level")
    difficulty1 = data.get("difficulty1")
    difficulty2 = data.get("difficulty2")
    difficulty3 = data.get("difficulty3")
    difficulty4 = data.get("difficulty4")
    difficulty5 = data.get("difficulty5")
    difficulty6 = data.get("difficulty6")
    difficulty7 = data.get("difficulty7")
    pred_prob = data.get("predicted_probability")
    result = data.get("result")
    number_sense = data.get("number_sense")
    counting = data.get("counting")
    arithmetic = data.get("arithmetic")
    visual_patterns = data.get("visual_patterns")
    memory = data.get("memory")
    stars = data.get("stars")
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S.%f")

    payload = {
        "player_id": player_id,
        "player": player,
        "level": level,
        "difficulty1": difficulty1,
        "difficulty2": difficulty2,
        "difficulty3": difficulty3,
        "difficulty4": difficulty4,
        "difficulty5": difficulty5,
        "difficulty6": difficulty6,
        "difficulty7": difficulty7,
        "prediction_probability": pred_prob,
        "result": result,
        "number_sense": number_sense,
        "counting": counting,
        "arithmetic": arithmetic,
        "visual_patterns": visual_patterns,
        "memory": memory,
        "stars": stars,
        "time_stamp": timestamp
    }
    print("JSON sent:", payload)

    topic = "s_player_data"

    success = send_sensor_data(topic, payload)

    return jsonify({
        "status": "ok" if success else "failed",
        "topic": topic,
        "payload": payload
    })

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)