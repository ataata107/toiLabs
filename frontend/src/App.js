import React, { useEffect, useState } from "react";
import SensorTable from "./components/SensorTable";
import { connect } from "./services/websocket";

function App() {
  const [sensorData, setSensorData] = useState([]);

  // 1. Load latest-per-device on initial mount
  useEffect(() => {
    fetch("/api/latest-per-device")
      .then((res) => res.json())
      .then((data) => {
        setSensorData(data);
      })
      .catch((err) => console.error("Failed to fetch initial data:", err));
  }, []);

  // 2. Listen to real-time updates via WebSocket
  useEffect(() => {
    const client = connect((newData) => {
      setSensorData((prevData) => {
        const existingIndex = prevData.findIndex(
          (item) => item.deviceId === newData.deviceId
        );

        if (existingIndex !== -1) {
          const updated = [...prevData];
          updated[existingIndex] = newData;
          return updated;
        } else {
          return [newData, ...prevData];
        }
      });
    });

    return () => client.deactivate();
  }, []);

  return (
    <div>
      <h1>ToiLabs Sensor Dashboard</h1>
      <SensorTable data={sensorData} />
    </div>
  );
}

export default App;
