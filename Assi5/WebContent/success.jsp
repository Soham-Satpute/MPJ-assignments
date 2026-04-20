<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.Employee" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:opsz,wght@9..40,300;9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        :root {
            --bg: #0f0e17; --surface: #1a1928; --surface2: #22203a;
            --accent: #4ecca3; --accent2: #e8c547;
            --text: #fffffe; --muted: #a7a9be; --border: #2e2c4a;
            --radius: 12px;
        }
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: 'DM Sans', sans-serif;
            background: var(--bg); color: var(--text);
            min-height: 100vh; display: flex;
            align-items: center; justify-content: center; padding: 20px;
        }
        .bg-blobs { position: fixed; inset: 0; z-index: 0; pointer-events: none; }
        .blob { position: absolute; border-radius: 50%; filter: blur(90px); opacity: 0.18; animation: float 12s ease-in-out infinite; }
        .blob-1 { width: 500px; height: 500px; background: #4ecca3; top: -200px; right: -100px; }
        .blob-2 { width: 400px; height: 400px; background: #6c5ce7; bottom: -100px; left: -80px; animation-delay: -5s; }
        @keyframes float { 0%,100%{transform:translateY(0);} 50%{transform:translateY(-25px);} }

        .card {
            position: relative; z-index: 1;
            width: 100%; max-width: 500px;
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: 20px; padding: 40px 44px;
            box-shadow: 0 30px 80px rgba(0,0,0,0.5);
            animation: popIn 0.7s cubic-bezier(0.16,1,0.3,1) both;
        }
        @keyframes popIn { from{opacity:0;transform:scale(0.9) translateY(30px);} to{opacity:1;transform:scale(1) translateY(0);} }

        .checkmark {
            width: 60px; height: 60px;
            background: rgba(78,204,163,0.12);
            border: 2px solid rgba(78,204,163,0.3);
            border-radius: 50%;
            display: flex; align-items: center; justify-content: center;
            font-size: 26px; margin-bottom: 20px;
            animation: checkPop 0.5s 0.3s cubic-bezier(0.16,1,0.3,1) both;
        }
        @keyframes checkPop { from{opacity:0;transform:scale(0);} to{opacity:1;transform:scale(1);} }

        h1 { font-family: 'DM Serif Display', serif; font-size: 1.85rem; line-height: 1.2; margin-bottom: 6px; }
        h1 em { font-style: italic; color: var(--accent); }
        .subtitle { color: var(--muted); font-size: 13.5px; margin-bottom: 28px; }

        .divider { height: 1px; background: var(--border); margin: 20px 0; }

        .detail-grid {
            display: grid; grid-template-columns: 1fr 1fr; gap: 14px;
            margin-bottom: 8px;
        }
        .detail-item {
            background: var(--surface2);
            border: 1px solid var(--border);
            border-radius: var(--radius); padding: 14px 16px;
        }
        .detail-item .dk {
            font-size: 10.5px; font-weight: 600;
            letter-spacing: 0.08em; text-transform: uppercase;
            color: var(--muted); margin-bottom: 4px;
        }
        .detail-item .dv {
            font-size: 14px; font-weight: 500; color: var(--text); word-break: break-all;
        }
        .detail-item.accent-item { border-color: rgba(78,204,163,0.3); }
        .detail-item.accent-item .dv { color: var(--accent); }

        .detail-item.full { grid-column: 1 / -1; }

        .actions { display: flex; gap: 12px; margin-top: 24px; }
        .btn-primary {
            flex: 1; padding: 13px;
            background: var(--accent2); color: #0f0e17;
            font-family: 'DM Sans', sans-serif; font-size: 14px; font-weight: 700;
            border: none; border-radius: var(--radius); cursor: pointer; text-decoration: none;
            text-align: center; display: block;
            transition: transform 0.15s, box-shadow 0.15s;
            box-shadow: 0 4px 20px rgba(232,197,71,0.25);
        }
        .btn-primary:hover { transform: translateY(-1px); box-shadow: 0 8px 24px rgba(232,197,71,0.35); }
        .btn-secondary {
            flex: 1; padding: 13px;
            background: transparent; color: var(--muted);
            font-family: 'DM Sans', sans-serif; font-size: 14px; font-weight: 500;
            border: 1.5px solid var(--border); border-radius: var(--radius);
            cursor: pointer; text-decoration: none; text-align: center; display: block;
            transition: border-color 0.2s, color 0.2s;
        }
        .btn-secondary:hover { border-color: var(--muted); color: var(--text); }
    </style>
</head>
<body>
<div class="bg-blobs">
    <div class="blob blob-1"></div>
    <div class="blob blob-2"></div>
</div>
<div class="card">
    <div class="checkmark">✓</div>
    <h1>Employee <em>Registered!</em></h1>
    <p class="subtitle">${message} Here's a summary of the details saved.</p>

    <div class="detail-grid">
        <div class="detail-item accent-item">
            <div class="dk">Employee ID</div>
            <div class="dv">#${employee.id}</div>
        </div>
        <div class="detail-item">
            <div class="dk">Department</div>
            <div class="dv">${employee.department}</div>
        </div>
        <div class="detail-item full">
            <div class="dk">Full Name</div>
            <div class="dv">${employee.name}</div>
        </div>
        <div class="detail-item full">
            <div class="dk">Email Address</div>
            <div class="dv">${employee.email}</div>
        </div>
        <div class="detail-item">
            <div class="dk">Phone</div>
            <div class="dv">${empty employee.phone ? '—' : employee.phone}</div>
        </div>
        <div class="detail-item">
            <div class="dk">Salary</div>
            <div class="dv">₹${employee.salary}</div>
        </div>
    </div>

    <div class="actions">
        <a href="index.jsp" class="btn-primary">+ Register Another</a>
        <a href="#" onclick="window.print()" class="btn-secondary">Print / Save</a>
    </div>
</div>
</body>
</html>
