import React, { useState, useEffect } from "react";
import axios from "axios";

function App() {

    const [searchTerm, setSearchTerm] = useState("");
    const [warehouse, setWarehouse] = useState([]);
    const [whproduct, setwhproduct] = useState([]);
    const [error, setError] = useState("");
    const [selectedWare, setSelectedWare] = useState(null);

    useEffect(() => {

            fetch("https://backend-awim.onrender.com/api/warehouse")
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Failed to fetch warehouse");
                }
                return response.json();
            })
            .then((data) => {
                console.log("Fetch data", data);
                setWarehouse(data);
            })
            .catch((error) => {
                console.log("Error dm may", error);
                setError(error.message);
            });


        fetch("https://backend-awim.onrender.com/api/warehousehasproduct")
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Failed to fetch warehouse");
                }
                return response.json();
            })
            .then((data) => {
                console.log("Fetch data", data);
                setwhproduct(data);
            })
            .catch((error) => {
                console.log("Error dm may", error);
                setError(error.message);
            });



    }, []);

    const filterWarehouse = warehouse.filter((warehouse) =>
        warehouse.name?.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleToggleDetails = (id) => {
        setSelectedWare((prev) => (prev) === id ? null : id);
    }

    return (
        <div>

            <div style={{ marginBottom: "20px" }}>
                <label>
                    <input
                        type = "text"
                        style={{ width: "300px", marginLeft: "10px" }}
                        placeholder="Lọc hóa đơn theo tên KH"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                </label>

            </div>


            <table>
                <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>address</th>
                    <th>manager</th>
                    <th>Chi tiết</th>
                </tr>
                </thead>
                <tbody>
                {filterWarehouse.map((warehouse) => (
                    <tr key={warehouse.id}>
                        <td>{warehouse.id}</td>
                        <td>{warehouse.name}</td>
                        <td>{warehouse.address}</td>
                        <td>{warehouse.manager}</td>
                        <td>
                            <button onClick={() => handleToggleDetails(warehouse.id)}>
                                {selectedWare === warehouse.id
                                    ? "Ẩn chi tiết"
                                    : "Xem chi tiết"}
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>


            {selectedWare && (
                <div>
                    <h2>Chi tiết Kho {selectedWare}

                    <table>
                        <thead>
                        <tr>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Giá</th>
                        </tr>
                        </thead>

                        <tbody>
                            {whproduct
                                .filter((invoice) => invoice.id === selectedWare)
                                .map((invoice) => (
                                    <tr key={invoice.product.id}>
                                        <td>{invoice.product.name}</td>
                                        <td>{invoice.product.quantity}</td>
                                        <td>{invoice.product.price}</td>
                                    </tr>
                                ))}
                        </tbody>

                    </table>

                    </h2>
                </div>
            )}


        </div>
    )
}

export default App;
